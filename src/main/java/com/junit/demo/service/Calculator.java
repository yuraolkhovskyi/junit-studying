package com.junit.demo.service;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    private int add(final int firstNumber, final int secondNumber) {
        return firstNumber + secondNumber;
    }

}
