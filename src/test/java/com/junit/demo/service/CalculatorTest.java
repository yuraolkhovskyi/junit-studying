package com.junit.demo.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.time.Duration.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayName(value = "Calculator test")
class CalculatorTest {

    public CalculatorTest(TestInfo testInfo) {
        assertEquals("Calculator test", testInfo.getDisplayName());
    }

    final Calculator calculator = new Calculator();
    final Person person = new Person("Grant", null, LocalDate.now());

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

    //Assumptions
    @Test
    void add_checkResult8() {
        final String firstName = person.getFirstName();

        assumeTrue("tesst".endsWith("t"));
        assumingThat("MIqq".endsWith("q"),
                () -> {
                    assertTrue("qqqq".endsWith("qq"));
                }
        );
    }

    @Test
    void testOnDev() {
        System.setProperty("ENV", "DEV");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        //remainder of test will proceed
    }

    @Test
    void testOnProd() {
        System.setProperty("ENV", "PROD");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")), "Failed because of assumption");
        //remainder of test will be aborted
    }


    //Custom conditions
    @EnabledIf(expression = "#{systemProperties['java.version'].startsWith('11')}")
    @Test
    void givenEnabledIfLiteral_WhenTrue_ThenTestExecuted() {
        assertTrue(true);
    }

    //Custom conditions
    @Test
    @DisabledIf("#{systemProperties['java.version'].startsWith('11')}")
    void givenDisabledIf_WhenTrue_ThenTestNotExecuted() {
        assertTrue(true);
    }


    @RepeatedTest(value = 10, name = "{displayName} -- Repetition - {currentRepetition} out of {totalRepetitions}")
    void repeatedTestExample() {
        assertTrue(true);
    }

    //constructors in junit5 tests TestInfo
    @Test
    void testInfoTestExample(final TestInfo testInfo) {
        final String displayName = testInfo.getDisplayName(); //if no @DisplayName specified then test method name + params
        final Set<String> tags = testInfo.getTags();
        final Optional<Class<?>> testClass = testInfo.getTestClass();
        final Optional<Method> testMethod = testInfo.getTestMethod();

        System.out.println(displayName+ "\n" + tags +"\n" + testClass +"\n" + testMethod);

        assertTrue(true);
    }

    //constructors in junit5 tests RepetitionInfo
    @RepeatedTest(5)
    void repetitionInfoExample(final RepetitionInfo repetitionInfo) {
        final int currentRepetition = repetitionInfo.getCurrentRepetition();
        final int totalRepetitions = repetitionInfo.getTotalRepetitions();

        System.out.println("Current repetition: " + currentRepetition + "\n" + "Number of reps:" +  +  totalRepetitions);

        assertTrue(true);
    }

    //constructors in junit5 tests Test Reporter
    @RepeatedTest(5)
    void repetitionInfoExample(final TestReporter testReporter) {
        //used just for additional info
        testReporter.publishEntry("a status message");
        testReporter.publishEntry(new HashMap<>(Map.of("1", "a")));
        testReporter.publishEntry("first param", "second param");

        System.out.println();

        assertTrue(true);
    }
    //Other parameter resolvers must be explicitly enabled by registering appropriate extensions via @ExtendWith.























































    //testing static methods in junit 5
    @Test
    @DisplayName("Should throw an exception upon failing to uncover mind-boggling mysteries")
    void testUncoverMysteries() {
        // Instantiate a MockedStatic in a try-with-resources block
        try (MockedStatic<MysteryBox> mb = Mockito.mockStatic(MysteryBox.class)) {
            // stub the static method that is called by the class under test
            MysteryBox.amaze("test");
            // the subject under test uses MysteryBox internally, to uncover an amazing secret
            // we make sure it throws an exception when the box fails to deliver
            assertEquals("1", "1");


            mb.verify(()-> MysteryBox.amaze("test"));

        }
        // the mock is not visible outside the block above
    }




}