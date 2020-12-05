package com.junit.demo.graphql.service;

import com.junit.demo.graphql.entity.Speaker;
import com.junit.demo.graphql.entity.SpeakerTalk;
import com.junit.demo.graphql.entity.Talk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpeakerService {

    private final TalkService talkService;

    private static final List<Speaker> speakers;
    private static final List<SpeakerTalk> talksBySpeaker;

    static {
        speakers = List.of(
                new Speaker(1L, "Jack Ma", "Java 15 best coder"),
                new Speaker(2L, "David Q", "GraphQL best coder"),
                new Speaker(3L, "Andrey J", "Junit 5 best coder")
        );

        talksBySpeaker = List.of(
                new SpeakerTalk(1L, 1L, 1L),
                new SpeakerTalk(2L, 2L, 2L),
                new SpeakerTalk(3L, 3L, 3L)
        );
    }

    public List<Speaker> findAll() {
        return speakers;
    }

    public List<Speaker> findAllSpeakersForTalk(Talk talk) {
        Optional<SpeakerTalk> st = this.findById(talk.getId());

        return List.of(speakers.stream().filter(e -> e.getId().equals(st.get().getSpeakerId())).findFirst().orElseThrow());

//        return st.stream()
//                .map(e -> speakers.get(e.getSpeakerId()))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
    }

    public Optional<SpeakerTalk> findById(final Long talkId) {
        return talksBySpeaker.stream().filter(e -> e.getId().equals(talkId)).findFirst();
    }

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