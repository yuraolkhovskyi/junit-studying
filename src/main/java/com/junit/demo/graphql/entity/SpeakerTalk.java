package com.junit.demo.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpeakerTalk {

    private Long id;
    private Long speakerId;
    private Long talkId;
}
