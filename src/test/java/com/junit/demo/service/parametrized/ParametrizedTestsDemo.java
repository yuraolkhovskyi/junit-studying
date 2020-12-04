package com.junit.demo.service.parametrized;

import com.junit.demo.service.Person;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParametrizedTestsDemo {


    @ParameterizedTest
    @ValueSource(ints = {1, 5, 7, 9, 2, 2, Integer.MIN_VALUE})
    void isPositive(final int numberToCheck) {
        Assertions.assertTrue(numberToCheck > ZERO.intValue());
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @NullAndEmptySource
    void isEmptyString(final String stringToCheck) {
        Assertions.assertTrue(Strings.isNullOrEmpty(stringToCheck));
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE,names = {"MINUTES", "SECONDS"})
    //modes: INCLUDE EXCLUDE MATCH_ALL MATCH_ANY
    void enumSourceTest(final ChronoUnit unit) {
        assertFalse(EnumSet.of(ChronoUnit.MINUTES, ChronoUnit.SECONDS).contains(unit));
    }


    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(final String argument) {
        assertNotNull(argument);
    }

    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana", "orange");
    }



    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }





    @ParameterizedTest
    @MethodSource("com.junit.demo.service.parametrized.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        System.out.println(tinyString);
        // test with tiny string
    }


    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "src/test/resources/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(final String country, final int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(final String argument) {
        assertNotNull(argument);
    }


    @ParameterizedTest
    @ValueSource(strings = "How to achieve")
    void testWithImplicitFallbackArgumentConversion(final Book book) {
        assertEquals("How to achieve", book.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
             "Jane, Doe, F, 1990-05-20",
             "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(final ArgumentsAccessor arguments) {
        final var person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.get(3, LocalDate.class));

        assertEquals("Doe", person.getLastName());
        assertEquals(1990, person.getBirthday().getYear());
    }
}

class StringsProviders {
    protected static Stream<String> tinyStrings() {
        return Stream.of(".", "oo", "OOO");
    }
}

class MyArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context) {
        return Stream.of("stringA", "stringB").map(Arguments::of);
    }
}


class Book {

    private final String title;

    private Book(String title) {
        new StringsProviders();
        this.title = title;
    }

    public static Book fromTitle(String title) {
        return new Book(title);
    }

    public String getTitle() {
        return this.title;
    }
}
