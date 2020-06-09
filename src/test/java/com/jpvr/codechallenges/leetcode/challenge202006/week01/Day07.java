package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * COIN CHANGE 2
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Example 1:
 * - Input: amount = 5, coins = [1, 2, 5]
 * - Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * - Input: amount = 3, coins = [2]
 * - Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * - Input: amount = 10, coins = [10]
 * - Output: 1
 *
 * Note:
 * You can assume that
 * - 0 <= amount <= 5000
 * - 1 <= coin <= 5000
 * - the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 */
public class Day07 {

    static Stream<Arguments> testParameters () {

        return Stream.of(
                Arguments.of( 1, new int[] {1}, 1),
                Arguments.of( 2, new int[] {2}, 1),

                Arguments.of( 10, new int[] {10}, 1),

                Arguments.of( 3, new int[] {2}, 0),

                Arguments.of( 2, new int[] {1,2}, 2),

                Arguments.of( 2, new int[] {1}, 1),

                Arguments.of( 5, new int[] {1,2,5}, 4),

                Arguments.of( 8, new int[] {1,2,6,8}, 8)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => A = {0}, B = {1}, expected = {2}")
    @MethodSource("testParameters")
    public void should_get_coin_calculus(int amount, int[] coins, int expectedOutput) {
        assertThat(change(amount, coins), is(expectedOutput));
    } // end void should_get_coin_calculus(int amount, int[] coins, int expectedOutput)

    /**
     * Runtime: 5 ms
     * Memory Usage: 38.2 MB
     */
    public int change(int amount, int[] coins) {
        int[] combinations = new int[amount+1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int i=coin; i<amount+1; i++) {
                combinations[i] += combinations[i-coin];
            } // end iteration
        } // end iteration

        return combinations[amount];
    } // end int change(int amount, int[] coins)
} // end class Day07