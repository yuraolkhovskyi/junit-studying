package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Speaker {

    private Long id;
    private String name;
    private String twitter;

}
