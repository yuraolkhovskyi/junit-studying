package com.junit.demo.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static java.time.Duration.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class CalculatorTest {

    final Calculator calculator = new Calculator();
    final Person person = new Person("Grant", null);

    //simple test
    @Test
    void add_checkResult() {
        assertEquals(2, calculator.add(1, 1));
    }

    //custom annotation
    @TestCustomAnnotation
    void add_checkResult2() {
        assertEquals(2, calculator.add(1, 1));
    }

    //Display name annotation
    @Test
    @DisplayName(value = "\uD83D\uDE31")
    void add_checkResult3() {
        assertEquals(2, calculator.add(1, 1));
    }

    //different Assertions examples
    @Test
    void add_checkResult4() {
        assertAll(
                () -> assertEquals(5, calculator.add(2, 3)),
                () -> assertEquals(-1, calculator.subtrack(2, 3))
        );

        assertAll(
                () -> {
                    final String firstName = person.getFirstName();
                    final String lastName = person.getLastName();

                    assertNull(lastName);
                    assertNotNull(firstName);
                    assertTrue(firstName.startsWith("G"));
                    assertFalse(firstName.endsWith("G"));


                }
        );
    }

    //Testing of throwing exception
    @Test
    void add_checkResult5() {
        final RuntimeException actual = assertThrows(RuntimeException.class, () -> calculator.add(null, 3));

        assertEquals("invalid first or second number", actual.getMessage());
    }

    //Testing of timeout
    @Test
    void add_checkResult6() {
        assertTimeout(ofNanos(1L), () -> calculator.add(2, 3));
        assertTimeout(ofMillis(1L), () -> calculator.add(2, 3));
        assertTimeout(ofSeconds(1L), () -> calculator.add(2, 3));
        assertTimeout(ofMinutes(1L), () -> calculator.add(2, 3));
        assertTimeout(ofHours(1L), () -> calculator.add(2, 3));
        assertTimeout(ofDays(1L), () -> calculator.add(2, 3));
    }

    //more readable variant of tests using third party libraries
    @Test
    void add_checkResult7() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Assumptions
    // TODO: 10/6/2020 where applicable ?
    @Test
    void add_checkResult8() {
        final String firstName = person.getFirstName();

        assumeTrue(firstName.endsWith("t"));
        assumeFalse(firstName.endsWith("q"));
        assumingThat(firstName.endsWith("q"),
                () -> {
                    assertTrue(firstName.endsWith("qq"));
                }
        );
    }

    //Disabling tests
    @Test
    @Disabled(value = "Disabled because of testing goals")
    void add_checkResult9() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Run tests using operating system conditions
    @Test
    @EnabledOnOs({OS.MAC, OS.LINUX, OS.MAC, OS.WINDOWS})
    void add_checkResult10() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Run tests using java runtime environment conditions
    @Test
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_11, JRE.JAVA_14})
    void add_checkResult11() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Run tests using java jre range conditions
    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_14)
    void add_checkResult12() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Run tests using disabling conditions
    @Test
    @DisabledForJreRange(max = JRE.JAVA_8)
    void add_checkResult13() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    //Run test using system property condition
    @Test
    @EnabledIfSystemProperty(named = "QT_ACCESSIBILITY", matches = "2")
    void add_checkResult14() {
        assertThat(calculator.subtrack(5, 3), is(equalTo(2)));
    }

    @Test
    @EnabledIf("condition")
    void onlyOnStagingServer() {
        System.out.println("222");
    }
}