package de.lysium.four;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

import static java.util.Map.entry;

public class Four {

    private static final int MAGIC = 4;

    public static void main(String[] args) {
        List<Integer> numbers = List.of(0, 1, 2, 3, 4, 12345, 2_124_456_678);
        for (int number : numbers) {
            fourIsMagic(number);
        }
    }

    private static void fourIsMagic(int number) {
        while (true) {
            if (number == MAGIC) {
                System.out.println("four is magic.");
                return;
            } else {
                String english = humanize(number);
                int characterCount = english.length();
                String characterCountEnglish = humanize(characterCount);
                System.out.print(english + " is " + characterCountEnglish + ", ");
                number = characterCount;
            }
        }
    }

    private static final Map<Integer, String> smallNumberStrings = Map.ofEntries(
            entry(0, "zero"),
            entry(1, "one"),
            entry(2, "two"),
            entry(3, "three"),
            entry(4, "four"),
            entry(5, "five"),
            entry(6, "six"),
            entry(7, "seven"),
            entry(8, "eight"),
            entry(9, "nine"),
            entry(10, "ten"),
            entry(11, "eleven"),
            entry(12, "twelve"),
            entry(13, "thirteen"),
            entry(14, "fourteen"),
            entry(15, "fifteen"),
            entry(16, "sixteen"),
            entry(17, "seventeen"),
            entry(18, "eighteen"),
            entry(19, "nineteen")
    );

    private static final Map<Integer, String> tenths = Map.of(
            2, "twenty",
            3, "thirty",
            4, "forty",
            5, "fifty",
            6, "sixty",
            7, "seventy",
            8, "eighty",
            9, "ninety"
    );

    private static final List<String> scaleNames = List.of(
            "hundred",
            "thousand",
            "million",
            "billion",
            "trillion",
            "quadrillion",
            "quintillion",
            "sextillion",
            "septillion"
    );

    protected static String humanize(int number) {
        ArrayList<String> result = new ArrayList<>();
        if (number < 0) {
            throw new IllegalArgumentException("negatives not supported");
        } else if (number < 20) {
            result.add(smallNumberStrings.get(number));
        } else {
            humanize100(number, result);
        }
        return String.join("-", result);
    }

    private static List<String> humanizeStep(int number, int scale, IntFunction<List<String>> addHumanizedScale) {
        ArrayList<String> result = new ArrayList<>();
        int scales = number / scale;
        if (scales > 0) {
            result.addAll(addHumanizedScale.apply(scales));
        }
        final int remainder = number - (scales * scale);
        if (remainder > 0) {
            result.add(humanize(remainder));
        }
        return result;
    }

    private static void humanize100(int number, ArrayList<String> result) {
        if (number < 100) {
            result.addAll(humanizeStep(number, 10, tenths -> List.of(Four.tenths.get(tenths))));
        } else if (number < 1000) {
            result.addAll(humanizeStep(number, 100, hundreds -> List.of(humanize(hundreds), "hundred")));
        } else {
            double power = Math.floor(Math.log10(number));
            double scale = Math.floor(power / 3.0);
            // as number is int, we can safely cast the round result to int
            final int illionNumber = (int) Math.round(Math.pow(10, scale * 3));
            result.addAll(humanizeStep(number, illionNumber, illions -> List.of(humanize(illions), scaleNames.get((int) scale))));
        }
    }
}
