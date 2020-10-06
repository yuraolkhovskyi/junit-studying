package com.junit.demo.service;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class Calculator {

    int add(final Integer firstNumber, final Integer secondNumber) {
        if (Objects.isNull(firstNumber) || Objects.isNull(secondNumber)){
            throw new RuntimeException("invalid first or second number");
        }
        return firstNumber + secondNumber;
    }

    int subtrack(final int firstNumber, final int secondNumber) {
        return firstNumber - secondNumber;
    }


}
