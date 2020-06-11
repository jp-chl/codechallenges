package com.jpvr.codechallenges.leetcode.challenge202006.week02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SORT COLORS
 *
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent
 * the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, t
 * hen overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class Day11 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {0, 1, 0}, new int[] {0, 0, 1}),
                Arguments.of(new int[] {1, 2, 0, 1, 1, 0}, new int[] {0, 0, 1, 1, 1, 2}),
                Arguments.of(new int[] {0, 2, 2, 2, 2, 1, 1, 1}, new int[] {0, 1, 1, 1, 2, 2, 2, 2}),
                Arguments.of(new int[] {2, 0, 2, 1, 1, 0}, new int[] {0, 0, 1, 1, 2, 2}),
                Arguments.of(new int[] {2, 1, 0}, new int[] {0, 1, 2})
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_sort_by_colors(int[] nums, int[] expectedOutput) {
        int[] copyNums = Arrays.copyOfRange(nums, 0, nums.length);
        sortColorsManualFill(copyNums);
        assertThat(copyNums, is(expectedOutput));
    } // end void should_sort_by_colors(int[] nums, int[] expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 39.8 MB
     */
    public void sortColors(int[] nums) {
        int colorRedCount = 0; // 0's
        int colorWhileCount = 0; // 1's
        int colorBlueCount = 0; // 2's

        for(int num : nums) {
            switch (num) {
                case 0:
                    colorRedCount++;
                    break;
                case 1:
                    colorWhileCount++;
                    break;
                case 2:
                    colorBlueCount++;
                    break;
            } // end switch
        } // end iteration

        Arrays.fill(nums, 0, colorRedCount, 0);
        Arrays.fill(nums, colorRedCount, colorRedCount+colorWhileCount, 1);
        Arrays.fill(nums, colorRedCount+colorWhileCount, colorRedCount+colorWhileCount+colorBlueCount, 2);
    } // end  void sortColors(int[] nums)

    /**
     * Runtime: 0 ms
     * Memory Usage: 39.6 MB
     */
    int i = 0;
    public void sortColorsManualFill(int[] nums) {
        int colorRedCount = 0; // 0's
        int colorWhileCount = 0; // 1's
        int colorBlueCount = 0; // 2's

        for(int num : nums) {
            switch (num) {
                case 0:
                    colorRedCount++;
                    break;
                case 1:
                    colorWhileCount++;
                    break;
                case 2:
                    colorBlueCount++;
                    break;
            } // end switch
        } // end iteration

        while(colorRedCount-- > 0) {
            nums[i++] = 0;
        } // end
        while(colorWhileCount-- > 0) {
            nums[i++] = 1;
        } // end
        while(colorBlueCount-- > 0) {
            nums[i++] = 2;
        } // end
    } // end

    /**
     * Runtime: 0 ms
     * Memory Usage: 39.2 MB
     */
    int tmp;
    public void sortColorsDutchApproach(int[] nums) {
        int p0 = 0;
        int curr = 0;
        int p2 = nums.length - 1;

        while(curr <= p2) {
            if ( nums[curr] == 0 ) {
                swap(nums, p0++, curr++);
            } else if ( nums[curr] == 2 ) {
                swap(nums, p2--, curr);
            } else {
                curr++;
            }
        } // end iteration
    } // end  void sortColorsDutchApproach(int[] nums)

    private void swap(int[] arr, int i, int j) {
        tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    } // end void swap(int[] arr, int i, int j)
} // end class Day11
