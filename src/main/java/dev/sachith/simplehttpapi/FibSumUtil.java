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

            // E(n) = 4 * E(n-1) + E(n-2)
            long nextEven = 4 * evenCurrent + evenPrevious;

            evenPrevious = evenCurrent;
            evenCurrent = nextEven;
        }

        return sum;
    }

//    public static void main(String[] args) {
//        FibSumUtil fibSumUtil = new FibSumUtil();
//        System.out.println("FibSum(5) = " + FibSumUtil.fibSumImproved(5));   // Expected: 2
//        System.out.println("FibSum(8) = " + FibSumUtil.fibSumImproved(8));   // Expected: 10
//        System.out.println("FibSum(9) = " + FibSumUtil.fibSumImproved(9));   // Expected: 10
//        System.out.println("FibSum(20) = " + FibSumUtil.fibSumImproved(20)); // Expected: 10
//        System.out.println("FibSum(36) = " + FibSumUtil.fibSumImproved(36)); // Expected: 44
//        System.out.println("FibSum(5000) = " + FibSumUtil.fibSumImproved(5000)); // Expected: 3382
//
//    }
}
