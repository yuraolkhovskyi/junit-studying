package com.junit.demo.graphql.kickstar.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {

    private String title;
    private int score;
    private boolean isNice;

}
