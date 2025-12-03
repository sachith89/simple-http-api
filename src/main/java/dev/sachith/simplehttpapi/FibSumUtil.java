package dev.sachith.simplehttpapi;

public class FibSumUtil {

    public int fibSum(int limit) {

        int sum = 0;

        int previous = 0;
        int current = 1;

        while (current <= limit) {

            if (current % 2 == 0) {
                sum += current;
            }

            int next = previous + current;
            previous = current;
            current = next;
        }

        return sum;
    }

    public long fibSumImproved(long limit) {

        long sum = 0;

        // F(0)
        long evenPrevious = 0;

        // F(3), the first non-zero even number
        long evenCurrent = 2;

        while (evenCurrent <= limit) {
            sum += evenCurrent;

            long nextEven = 4 * evenCurrent + evenPrevious;

            evenPrevious = evenCurrent;
            evenCurrent = nextEven;
        }

        return sum;
    }

}
