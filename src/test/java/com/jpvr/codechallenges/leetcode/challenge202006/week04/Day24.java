package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * UNIQUE BINARY SEARCH TREES
 *
 * Given n, how many structurally unique BST's (binary search trees)
 * that store values 1 ... n?
 *
 * Example:
 * - Input: 3
 * - Output: 5
 *
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class Day24 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(3, 5),
                Arguments.of(4, 14),
                Arguments.of(5, 42),
                Arguments.of(6, 132),
                Arguments.of(7, 429)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => nums = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_get_unique_binary_search_trees(int n, int expectedOutput) {
        assertThat(numTrees(n), is(expectedOutput));
    } // end void should_get_unique_binary_search_trees(int n, int expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 36.3 MB
     */
    public int numTrees(int n) {

        if ( n < 2 ) {
            return 1;
        }

        int[] result = new int[n+1];
        result[0] = 1;
        result[1] = 1;

        for(int i = 2; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                result[i] += result[j] * result[i - j - 1];
            }
        }

        return result[n];
    } // end int numTrees(int n)
} // end class Day24