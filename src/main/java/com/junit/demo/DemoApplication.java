package com.junit.demo;

import com.coxautodev.graphql.tools.SchemaParser;
import com.junit.demo.graphql.kickstar.resolver.Mutation;
import com.junit.demo.graphql.kickstar.resolver.Query;
import com.junit.demo.graphql.kickstar.resolver.TalkResolver;
import com.junit.demo.graphql.kickstar.service.AttendeeService;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import com.junit.demo.graphql.kickstar.service.TalkService;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}


    //GraphQl
    private final SpeakerService speakerService;
    private final AttendeeService attendeeService;
    private final TalkService talkService;

    public DemoApplication(final SpeakerService speakerService,
                           final AttendeeService attendeeService,
                           final TalkService talkService) {
        this.speakerService = speakerService;
        this.attendeeService = attendeeService;
        this.talkService = talkService;
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean graphQLServlet() {
        return new ServletRegistrationBean(SimpleGraphQLHttpServlet.newBuilder(buildSchema(speakerService, attendeeService, talkService))
                .build(), "/graphql");
    }

    private static GraphQLSchema buildSchema(final SpeakerService speakerService,
                                             final AttendeeService attendeeService,
                                             final TalkService talkService) {
        return SchemaParser
                .newParser()
                .file("graphql/schema.graphqls")
                .resolvers(
                        new Query(talkService, attendeeService, speakerService),
                        new TalkResolver(speakerService),
                        new Mutation(speakerService))
                .build()
                .makeExecutableSchema();
    }


}
