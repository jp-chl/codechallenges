package com.jpvr.codechallenges.leetcode.challenge202006.week03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * PERMUTATION SEQUENCE
 *
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order,
 * we get the following sequence for n = 3:
 *
 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 * Given n and k, return the kth permutation sequence.
 *
 * Note:
 * - Given n will be between 1 and 9 inclusive.
 * - Given k will be between 1 and n! inclusive.
 *
 * Example 1:
 * - Input: n = 3, k = 3
 * - Output: "213"
 *
 * Example 2:
 * - Input: n = 4, k = 9
 * - Output: "2314"
 */
public class Day20 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(3, 3, "213"),
                Arguments.of(4, 9, "2314")
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, k = {1}, expected = {2}")
    @MethodSource("testParameters")
    public void should_get_permutation_sequence(int n, int k, String expectedOutput) {
        assertThat(getPermutation(n, k), is(expectedOutput));
    } // end void should_get_permutation_sequence(int n, int k, String expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 37.1 MB
     */
    private String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        int i;
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> num = new ArrayList<Integer>();

        for (i = 0; i < n; i++) {
            num.add(i + 1);
            factorial[i] = i == 0 ? 1 : i * factorial[i - 1];
        } // end iteration

        while (n > 0) {
            int remaining = ((k - 1) % factorial[n - 1]) + 1;
            sb.append(num.remove(((k - 1) / factorial[n - 1])));
            n--;
            k = remaining;
        } // end iteration

        return sb.toString();
    } // end String getPermutation(int n, int k)
} // end class Day20