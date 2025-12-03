package dev.sachith.simplehttpapi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibSumUtilTest {

    private static final String VALID_INPUTS = """
                    10, 10,           Small limit
                    100, 44,          Large limit
                    1, 0,             No even numbers (Fib: 1)
                    2, 2,             Limit is exactly the first even Fib
                    8, 10,            Limit is an even Fib number
                    5000, 3382,       Larger number
                    0, 0,             Zero limit
                    4000000, 4613732, Project Euler standard test case
            """;

    @ParameterizedTest(name = "fibSum: limit={0}, expectedSum={1} ({2})")
    @CsvSource(textBlock = VALID_INPUTS)
    void testFibSum(int limit, int expectedSum, String description) {
        FibSumUtil fibSumUtil = new FibSumUtil();
        double result = fibSumUtil.fibSum(limit);
        assertEquals(expectedSum, result,
                () -> String.format("The sum of even Fibonacci numbers up to %d should be %d.", limit, expectedSum));
    }

    @ParameterizedTest(name = "fibSumImproved: limit={0}, expectedSum={1} ({2})")
    @CsvSource(textBlock = VALID_INPUTS)
    void testFibSumImproved(int limit, double expectedSum, String description) {
        FibSumUtil fibSumUtil = new FibSumUtil();
        double result = fibSumUtil.fibSumImproved(limit);
        assertEquals(expectedSum, result,
                () -> String.format("The sum of even Fibonacci numbers up to %d should be %.0f.", limit, expectedSum));
    }

    @ParameterizedTest(name = "Negative Input: limit={0}")
    @CsvSource({"-1", "-10", "-50"})
    void testNegativeInputs(int limit) {
        FibSumUtil fibSumUtil = new FibSumUtil();
        assertEquals(0, fibSumUtil.fibSum(limit), "Negative limit should result in 0 sum");
        assertEquals(0, fibSumUtil.fibSumImproved(limit), "Negative limit should result in 0 sum");
    }
}