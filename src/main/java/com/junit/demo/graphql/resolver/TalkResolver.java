package com.junit.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.junit.demo.graphql.entity.Speaker;
import com.junit.demo.graphql.entity.Talk;
import com.junit.demo.graphql.service.SpeakerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TalkResolver implements GraphQLResolver<Talk> {

    private final SpeakerService speakerService;

    public List<Speaker> speakers(final Talk talk) {
        return speakerService.findAllSpeakersForTalk(talk);
    }


//    speakers: [Speaker]
}
