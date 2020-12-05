package com.junit.demo.graphql.kickstar.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.junit.demo.graphql.kickstar.entity.Speaker;
import com.junit.demo.graphql.kickstar.entity.Talk;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TalkResolver implements GraphQLResolver<Talk> {

    private final SpeakerService speakerService;

    public List<Speaker> speakers(final Talk talk) {
        return speakerService.findAllSpeakersForTalk(talk);
    }

}
