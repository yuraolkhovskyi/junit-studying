package com.junit.demo.graphql.kickstar.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.junit.demo.graphql.kickstar.entity.Attendee;
import com.junit.demo.graphql.kickstar.entity.Speaker;
import com.junit.demo.graphql.kickstar.entity.Talk;
import com.junit.demo.graphql.kickstar.service.AttendeeService;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import com.junit.demo.graphql.kickstar.service.TalkService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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

    public List<Object> allAll() {
        List list1 = talkService.findAll();
        List list2 = speakerService.findAll();

        List union = new ArrayList();

        union.addAll(list1);
        union.addAll(list2);

        return union;
    }

}
