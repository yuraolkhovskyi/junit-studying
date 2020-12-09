package com.junit.demo.graphql.kickstar.service;

import com.junit.demo.graphql.kickstar.entity.Talk;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Talk> findById(final Long talkId) {
        return talks.stream().filter(e -> e.getId().equals(talkId)).findFirst();
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
