package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attendee implements Human {

    private Long id;
    private String name;

}