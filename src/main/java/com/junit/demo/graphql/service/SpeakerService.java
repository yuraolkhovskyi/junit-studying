package com.junit.demo.graphql.service;

import com.junit.demo.graphql.entity.Speaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerService {

    private static final List<Speaker> speakers;

    static {
        speakers = List.of(
                new Speaker(1L, "Jack Ma", "Java 15 features"),
                new Speaker(2L, "David Q", "GraphQL features"),
                new Speaker(3L, "Andrey J", "Junit features")
        );
    }

    public List<Speaker> findAll() {
        return speakers;
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
