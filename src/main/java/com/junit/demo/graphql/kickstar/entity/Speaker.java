package com.junit.demo.graphql.kickstar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Speaker implements Human {

    private Long id;
    private String name;
    private String twitter;
    private Rate rate;

}
