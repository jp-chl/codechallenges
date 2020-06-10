package com.jpvr.codechallenges.leetcode.challenge202006.week02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SEARCH INSERT POSITION
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 * - Input: [1,3,5,6], 5
 * - Output: 2
 *
 * Example 2:
 * - Input: [1,3,5,6], 2
 * - Output: 1
 *
 * Example 3:
 * - Input: [1,3,5,6], 7
 * - Output: 4
 *
 * Example 4:
 * - Input: [1,3,5,6], 0
 * - Output: 0
 */
public class Day10 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {1}, 1, 0),
                Arguments.of(new int[] {1, 9, 17, 25, 81}, 7, 1),
                Arguments.of(new int[] {1, 9, 17, 25, 81}, 35, 4),
                Arguments.of(new int[] {1, 10, 21, 32, 43, 54, 65, 76, 87, 98, 103, 243}, 89, 9),


                Arguments.of(new int[] {1}, 0, 0),
                Arguments.of(new int[] {1}, 2, 1),
                Arguments.of(new int[] {}, 0, 0),
                Arguments.of(new int[] {}, 1, 0),

                Arguments.of(new int[] {1,3}, 2, 1),
                Arguments.of(new int[] {1,3}, 1, 0),
                Arguments.of(new int[] {1,3}, 3, 1),

                Arguments.of(new int[] {1,3,5,6}, 5, 2),
                Arguments.of(new int[] {1,3,5,6}, 2, 1),
                Arguments.of(new int[] {1,3,5,6}, 7, 4),
                Arguments.of(new int[] {1,3,5,6}, 0, 0)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_search_insert_position(int[] nums, int target, int expectedOutput) {

        assertThat(searchInsertBest(nums, target), is(expectedOutput));
    } // end void should_validate_is_subsequence(String s, String t, boolean expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 38.6 MB
     */
    public int searchInsertBest(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        int mid = (low+high)/2;

        while( low <= high )  {

            if( nums[mid] == target ) {
                return mid;
            }

            if( nums[mid] < target ) {
                low = mid + 1;
            }  else {
                high = mid - 1;
            }

            mid = (low+high)/2;
        } // end iteration

        return low;
    } // end int searchInsertBest(int[] nums, int target)

    /**
     * Runtime: 0 ms
     * Memory Usage: 38.9 MB
     */
    public int searchInsert(int[] nums, int target) {
        if ( nums == null ) {
            return 0;
        }
        final int length = nums.length;
        if ( length == 0 ) {
            return 0;
        }

        int low = 0;
        int high = length-1;

        if ( target <= nums[low] ) {
            return 0;
        }
        if ( target > nums[high] ) {
            return length;
        }

        int mid = low + (high-low)/2;
        int middleValue = nums[mid];

        while(low < high) {

            middleValue = nums[mid];
            if ( middleValue == target ) {
                return mid;
            }

            if ( target > middleValue ) {
                low = mid;
            }
            if ( target < middleValue ) {
                high = mid;
            }

            mid = low + (high-low)/2;

            if ( mid == low  ||  mid == high ) {
                if ( nums[low] == target ) {
                    return low;
                }
                if ( nums[high] == target) {
                    return high;
                }
                break;
            } // end
        } // end iteration

        return low + 1;
    } // end int searchInsert(int[] nums, int target)

} // end class Day10