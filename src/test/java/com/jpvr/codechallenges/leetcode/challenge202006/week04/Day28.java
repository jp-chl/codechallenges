package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * PERFECT SQUARES
 *
 * Given a positive integer n,
 * find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 * - Input: n = 12
 * - Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * - Input: n = 13
 * - Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class Day28 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(12, 3),
                Arguments.of(13, 2)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_get_least_number_of_perfect_square_numbers(int n, int expectedOutput) {
        assertThat(numSquaresBest(n), is(expectedOutput));
    } // end void should_get_least_number_of_perfect_square_numbers(int n, int expectedOutput)

    /**
     * Runtime: 2 ms
     * Memory Usage: 38.6 MB
     *
     */
    private int numSquaresBest(int n) {

        if (isSquare(n)) {
            return 1;
        }

        int sqrt = (int) Math.sqrt(n);

        for (int i = 1; i <= sqrt; i ++) {
            if ( isSquare(n - i * i) ) {
                return 2;
            }
        }

        while (n % 4 == 0) {
            n >>= 2;
        }
        if (n % 8 == 7) {
            return 4;
        }
        return 3;
    } // end

    private boolean isSquare(int n) {
        final int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    } // end boolean isSquare(int n)

    /**
     * Runtime: 46 ms
     * Memory Usage: 39.8 MB
     */
    public int numSquares(int n) {

        int[] dp = new int[n+1];

        for(int i=1; i<=n; ++i) {
            int min = i;
            int y = 1;
            int square = 1;
            while( square <= i ) {
                min = Math.min(min, 1 + dp[i - square]);
                y++;
                square = y*y;
            } // end iteration
            dp[i] = min;
        } // end iteration

        return dp[n];
    } // end int numSquares(int n)
} // end class Day28





























































