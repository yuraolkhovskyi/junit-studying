package com.junit.demo.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;

@TestMethodOrder(MethodOrderer.Alphanumeric.class) //sorts test methods numerically based on values specified via the @Order annotation.
//MethodOrderer.Random.class --- orders test methods pseudo-randomly and supports configuration of a custom seed.
//MethodOrderer.Alphanumeric.class --- sorts test methods alphanumerically based on their names and formal parameter lists. Deprecated in favor of MethodName. Will be removed in 6.0
public class TestExecutionOrder {

    @Test
    @Order(1)
    public void nullValues() {
        System.out.println("Test 1");
    }

    @Test
    @Order(2)
    void emptyValues() {
        System.out.println("Test 2");
    }

    @Test
    @Order(3)
    void validValues() {
        System.out.println("Test 3");
    }


//    junit.jupiter.testmethod.order.default = org.junit.jupiter.api.MethodOrderer$OrderAnnotation


}
