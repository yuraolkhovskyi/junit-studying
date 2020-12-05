package com.junit.demo.graphql.kickstar.service;

import com.junit.demo.graphql.kickstar.entity.Attendee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    private static final List<Attendee> attendees;

    static {
        attendees = List.of(
                new Attendee(1L, "First Attendee"),
                new Attendee(2L, "Second Attendee"),
                new Attendee(3L, "Third Attendee")
        );
    }


    public List<Attendee> findAll() {
        return attendees;
    }


}
