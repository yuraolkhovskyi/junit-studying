package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeakerTalk {

    private Long id;
    private Long speakerId;
    private Long talkId;
}
