package com.jpvr.codechallenges.leetcode.challenge202006.week02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * POWER OF TWO
 *
 * Given an integer, write a function
 * to determine if it is a power of two.
 *
 * Example 1:
 *
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 *
 * Input: 218
 * Output: false
 */
public class Day08 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(536870912, true),
                Arguments.of(0, false),
                Arguments.of(-16, false),
                Arguments.of( 16, true),

                Arguments.of( 1, true),
                Arguments.of( 2, true),
                Arguments.of( 3, false),
                Arguments.of( 4, true),
                Arguments.of( 5, false),

                Arguments.of( 128, true),
                Arguments.of( 218, false),
                Arguments.of( 7, false)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_validate_power_of_two(int n, boolean expectedOutput) {

        assertThat(isPowerOfTwoBits(n), is(expectedOutput));
    } // end void should_validate_power_of_two(int n, boolean expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 36.7 MB
     */
    public boolean isPowerOfTwo(int n) {
        if ( n == 1 ) {
            return true;
        }

        int m = n % 2;
        if ( m != 0 ) {
            return false;
        }

        while( n > 2  &&  m % 2 == 0) {
            n /= 2;
            m = n % 2;
        } // end iteration

        return n == 2;
    } // end boolean isPowerOfTwo(int n)

    /**
     * Runtime: 2 ms
     * Memory Usage: 38.1 MB
     */
    public boolean isPowerOfTwoBits(int n) {
        return (n>0) && ((n&(-n)) == n);
    } // end boolean isPowerOfTwoBits(int n)

    /**
     * Runtime: 6 ms
     * Memory Usage: 37.9 MB
     */
    final double MAX_ERROR =  1E-10;
    public boolean isPowerOfTwoLog(int n) {
        if ( n <= 0) return false;
        final double log = Math.log((double)n) / Math.log(2);

        double logToUse = Math.round(log);
        if ( Math.abs(log - logToUse) > MAX_ERROR ) {
            logToUse = log;
        }

        return Math.ceil(logToUse) == Math.floor(logToUse);
    } // end

    private String decimalAsBinary(int n) {

        StringBuilder result = new StringBuilder();

        while(n>0) {
            result = new StringBuilder().append(n%2).append(result);

            n /= 2;
        } // end iteration

        return result.toString();
    } // end String decimalAsBinary(int n)

} // end class Day08