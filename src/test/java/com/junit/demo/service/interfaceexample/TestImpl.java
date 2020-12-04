package com.junit.demo.service.interfaceexample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestImpl implements TestInterfaceDynamicTestsDemo, TestLifecycleLogger {


    @Test
    public void isEqualValue() {
        int length = "a".length();
        assertEquals(1, length, "is always equal");
    }
}
