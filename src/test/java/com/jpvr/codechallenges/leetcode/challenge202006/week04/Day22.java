package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SINGLE NUMBER II
 *
 * Solution
 * Given a non-empty array of integers, every element appears three times except for one,
 * which appears exactly once. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 *
 * Example 1:
 * - Input: [2,2,3,2]
 * - Output: 3
 *
 * Example 2:
 * - Input: [0,1,0,1,0,1,99]
 * - Output: 99
 */
public class Day22 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {2, 2, 3, 2}, 3),
                Arguments.of(new int[] {0, 1, 0, 1, 0, 1, 99}, 99),
                Arguments.of(new int[] {0, 0, 0, 5}, 5)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => nums = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_get_single_number(int[] nums, int expectedOutput) {
        assertThat(singleNumber(nums), is(expectedOutput));
    } // end void should_get_single_number(int[] nums, int expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 39.6 MB
     */
    public int singleNumber(int[] nums) {

        final int length = nums.length;
        if ( length < 3 ) {
            return nums[0];
        }

        Arrays.sort(nums);
        if ( nums[0] != nums[1] ) {
            return nums[0];
        }

        int i = 1;
        while(i < length) {
            if ( nums[i] != nums[i-1] ) {
                return nums[i-1];
            }

            i += 3;
        } // end iteration

        return nums[length-1];
    } // end int singleNumber(int[] nums)
} // end class Day22