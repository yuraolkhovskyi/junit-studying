package com.junit.demo.graphql.kickstar.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.junit.demo.graphql.kickstar.entity.Attendee;
import com.junit.demo.graphql.kickstar.entity.Human;
import com.junit.demo.graphql.kickstar.entity.Speaker;
import com.junit.demo.graphql.kickstar.entity.Talk;
import com.junit.demo.graphql.kickstar.service.AttendeeService;
import com.junit.demo.graphql.kickstar.service.SpeakerService;
import com.junit.demo.graphql.kickstar.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Resource
    private TalkService talkService;
    @Resource
    private AttendeeService attendeeService;
    @Resource
    private SpeakerService speakerService;

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

    public List<Human> allHumans() {
        List<Attendee> all = attendeeService.findAll();
        List<Speaker> all1 = speakerService.findAll();

        List list = new ArrayList();
        list.addAll(all);
        list.addAll(all1);

        return list;
    }

}
