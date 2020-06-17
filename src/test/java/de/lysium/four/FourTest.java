package de.lysium.four;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static de.lysium.four.Four.humanize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FourTest {


    private static Stream<Arguments> provideHumanize() {
        return Stream.of(
                Arguments.of(1, "one"),
                Arguments.of(2, "two"),
                Arguments.of(10, "ten"),
                Arguments.of(20, "twenty"),
                Arguments.of(23, "twenty-three"),
                Arguments.of(82, "eighty-two"),
                Arguments.of(100, "one-hundred"),
                Arguments.of(103, "one-hundred-three"),
                Arguments.of(316, "three-hundred-sixteen"),
                Arguments.of(123, "one-hundred-twenty-three"),
                Arguments.of(543, "five-hundred-forty-three"),
                Arguments.of(987, "nine-hundred-eighty-seven"),
                Arguments.of(1001, "one-thousand-one"),
                Arguments.of(2345, "two-thousand-three-hundred-forty-five"),
                Arguments.of(34567, "thirty-four-thousand-five-hundred-sixty-seven"),
                Arguments.of(456700, "four-hundred-fifty-six-thousand-seven-hundred"),
                Arguments.of(900000, "nine-hundred-thousand"),
                Arguments.of(909090, "nine-hundred-nine-thousand-ninety"),
                Arguments.of(1909090, "one-million-nine-hundred-nine-thousand-ninety"),
                Arguments.of(48_080_807, "forty-eight-million-eighty-thousand-eight-hundred-seven"),
                Arguments.of(1_987_654_316, "one-billion-nine-hundred-eighty-seven-million-six-hundred-fifty-four-thousand-three-hundred-sixteen"),
                Arguments.of(999999, "nine-hundred-ninety-nine-thousand-nine-hundred-ninety-nine")
        );
    }

    @ParameterizedTest
    @MethodSource("provideHumanize")
    void humanizeTest(int number, String expected) {
        String actual = humanize(number);
        assertEquals(expected, actual);
    }
}