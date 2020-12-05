package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerInput {

    private String name;
    private String twitter;

}
