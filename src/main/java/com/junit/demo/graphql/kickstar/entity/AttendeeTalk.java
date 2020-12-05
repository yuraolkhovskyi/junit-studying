package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendeeTalk {

    private Long id;
    private Long attendeeId;
    private Long talkId;
}