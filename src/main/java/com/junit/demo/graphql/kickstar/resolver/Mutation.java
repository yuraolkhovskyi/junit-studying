package com.junit.demo.graphql.kickstar.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.junit.demo.graphql.kickstar.entity.Speaker;
import com.junit.demo.graphql.kickstar.entity.SpeakerInput;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Resource
    private SpeakerService speakerService;


    public Speaker addSpeaker(SpeakerInput speakerInput) {
        Speaker speaker = new Speaker(new Random().nextLong(), speakerInput.getName(), speakerInput.getTwitter());
        SpeakerService.speakers.add(speaker);
        return speaker;
    }

}
