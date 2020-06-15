package com.jpvr.codechallenges.leetcode.challenge202006.week02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * LARGEST DIVISIBLE SUBSET
 *
 * Given a set of distinct positive integers,
 * find the largest subset such that every pair (Si, Sj)
 * of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions,
 * return any subset is fine.
 *
 * Example 1:
 * - Input: [1,2,3]
 * - Output: [1,2] (of course, [1,3] will also be ok)
 *
 * Example 2:
 * - Input: [1,2,4,8]
 * - Output: [1,2,4,8]
 */
public class Day13 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {1,2,3}, Arrays.asList(1, 2), Arrays.asList(1, 3)),
                Arguments.of(new int[] {1,2,4,8}, Arrays.asList(1, 2, 4, 8), null)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => input = {0}, expected1 = {1}, expected2 = {2}")
    @MethodSource("testParameters")
    public void should_find_largest_divisible_subset(int[] nums, List<Integer> expected1, List<Integer> expected2) {
        assertThat(largestDivisibleSubset(nums), anyOf(equalTo(expected1), equalTo(expected2)));
    } // end void should_find_largest_divisible_subset(int input, int expected1, int expected2)

    /**
     * Runtime: 16 ms
     * Memory Usage: 39.3 MB
     */
    private List<Integer> largestDivisibleSubset(int[] nums) {

        final int length = nums.length;
        int[] longest = new int[length];
        int[] next = new int[length];
        int start = Integer.MIN_VALUE;
        int max = 0;

        Arrays.fill(next, -1);
        Arrays.sort(nums);

        for (int i = length - 1; i >= 0; --i) {
            for (int j = i + 1; j < length; ++j) {
                if (longest[j] > longest[i] && nums[j] % nums[i] == 0) {
                    longest[i] = longest[j];
                    next[i] = j;
                }
            } // end j iteration

            ++longest[i];
            if (longest[i] > max) {
                max = longest[i];
                start = i;
            }
        } // end i iteration

        List<Integer> result = new ArrayList<>();
        while (max-- > 0) {
            result.add(nums[start]);
            start = next[start];
        } // end while iteration

        return result;
    } // end List<Integer> largestDivisibleSubset(int[] nums)
} // end class Day13
