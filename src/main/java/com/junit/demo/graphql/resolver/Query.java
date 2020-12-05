package com.junit.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.junit.demo.graphql.entity.Talk;
import com.junit.demo.graphql.service.TalkService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final TalkService talkService;

    public List<Talk> allTalks() {
        return talkService.findAll();
    }

}
