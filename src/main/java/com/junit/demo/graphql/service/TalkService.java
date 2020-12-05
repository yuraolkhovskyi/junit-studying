package com.junit.demo.graphql.service;

import com.junit.demo.graphql.entity.Talk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkService {

    private static final List<Talk> talks;

    static {
        talks = List.of(
                new Talk(1L, "Java Conference", "Java 15 features"),
                new Talk(2L, "GraphQL Conference", "GraphQL features"),
                new Talk(3L, "Junit 5 Conference", "Junit features")
        );
    }

    public List<Talk> findAll() {
        return talks;
    }

//    public List<Talk> findAllTalksBySpeaker(Speaker speaker) {
//        List<SpeakerTalk> st = speakerTalkRepository.findAllBySpeakerId(speaker.getId());
//
//        return st.stream()
//                .map(e -> talkRepository.findById(e.getTalkId()))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

//    public List<Talk> findAllTAlksByAttendee(Attendee attendee) {
//        List<AttendeeTalk> st = attendeeTalkRepository.findAllByAttendeeId(attendee.getId());
//
//        return st.stream()
//                .map(e -> talkRepository.findById(e.getTalkId()))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//
//    }
}
