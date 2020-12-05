package com.junit.demo.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.junit.demo.graphql.entity.Attendee;
import com.junit.demo.graphql.entity.Speaker;
import com.junit.demo.graphql.entity.Talk;
import com.junit.demo.graphql.service.AttendeeService;
import com.junit.demo.graphql.service.SpeakerService;
import com.junit.demo.graphql.service.TalkService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final TalkService talkService;
    private final AttendeeService attendeeService;
    private final SpeakerService speakerService;

    public List<Talk> allTalks() {
        return talkService.findAll();
    }

    public List<Attendee> allAttendees() {
        return attendeeService.findAll();
    }

    public List<Speaker> allSpeakers() {
        return speakerService.findAll();
    }
}
