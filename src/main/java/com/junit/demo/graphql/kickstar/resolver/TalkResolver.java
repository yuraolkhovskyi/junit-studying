package com.junit.demo.graphql.kickstar.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.junit.demo.graphql.kickstar.entity.Speaker;
import com.junit.demo.graphql.kickstar.entity.Talk;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TalkResolver implements GraphQLResolver<Talk> {

    @Resource
    private SpeakerService speakerService;

    public List<Speaker> speakers(final Talk talk) {
        return speakerService.findAllSpeakersForTalk(talk);
    }

}
