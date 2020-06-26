package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * FIND THE DUPLICATE NUMBER
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 *
 * Example 1:
 * - Input: [1,3,4,2,2]
 * - Output: 2
 *
 * Example 2:
 * - Input: [3,1,3,4,2]
 * - Output: 3
 *
 * Note:
 * - You must not modify the array (assume the array is read only).
 * - You must use only constant, O(1) extra space.
 * - Your runtime complexity should be less than O(n2).
 * - There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class Day25 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {1, 3, 4, 2, 2}, 2),
                Arguments.of(new int[] {3, 1, 3, 4, 2}, 3)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => nums = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_find_duplicated_number(int[] nums, int expectedOutput) {
        assertThat(findDuplicate(nums), is(expectedOutput));
    } // end void should_find_duplicated_number(int[] nums, int expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 39.4 MB
     */
    private int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Find the "entrance" to the cycle.
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        } // end iteration

        return fast;
    } // end int findDuplicate(int[] nums)

    /**
     * Runtime: 4 ms
     * Memory Usage: 43.8 MB
     */
    private int findDuplicateSet(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            if ( set.contains(num) ) {
                return num;
            } else {
                set.add(num);
            }
        } // end iteration

        return -1;
    } // end int findDuplicate(int[] nums)

    /**
     * Runtime: 5 ms
     * Memory Usage: 43.4 MB
     */
    private int findDuplicateHashMap(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int value = nums[0];
        for(int num : nums) {
            value = map.getOrDefault(num, 0) + 1;
            if ( value > 1 ) {
                return num;
            }
            map.put(num, value);
        } // end iteration

        return -1;
    } // end int findDuplicate(int[] nums)

} // end class Day25
