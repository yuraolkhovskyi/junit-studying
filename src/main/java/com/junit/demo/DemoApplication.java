package com.junit.demo;

import com.coxautodev.graphql.tools.SchemaParser;
import com.junit.demo.graphql.resolver.Query;
import com.junit.demo.graphql.resolver.TalkResolver;
import com.junit.demo.graphql.service.AttendeeService;
import com.junit.demo.graphql.service.SpeakerService;
import com.junit.demo.graphql.service.TalkService;
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

    private static GraphQLSchema buildSchema(SpeakerService speakerService,
                                             AttendeeService attendeeService,
                                             TalkService talkService) {
        return SchemaParser
                .newParser()
                .file("graphql/schema.graphqls")
//                .dictionary()
                .resolvers(
                        new Query(talkService, attendeeService, speakerService),
                        new TalkResolver(speakerService))
                .build()
                .makeExecutableSchema();
    }


}
