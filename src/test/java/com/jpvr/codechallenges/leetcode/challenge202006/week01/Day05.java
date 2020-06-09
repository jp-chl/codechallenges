package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * RANDOM PICK WITH WEIGHT
 *
 * Given an array w of positive integers, where w[i] describes the weight of index i,
 * write a function pickIndex which randomly picks an index in proportion to its weight.
 *
 * Note:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments.
 * Solution's constructor has one argument, the array w. pickIndex has no arguments.
 * Arguments are always wrapped with a list, even if there aren't any.
 */
public class Day05 {

    @Test
    void test() {

        Map<Integer, Integer> map = new HashMap<>();

        int[] weights = new int[] {3, 14, 1, 7};
        Solution solution = new Solution(weights);

        int p = 0;
        for(int i=0; i<5000; ++i) {
            p = solution.pickIndex();
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
    } // end void test()

    /**
     * Runtime: 19 ms
     * Memory Usage: 43.8 MB
     */
    class Solution {

        private final Random random;
        private int[] weights;

        public Solution(int[] w) {
            random = new Random();

            for(int i=1; i<w.length; ++i) {
                w[i] += w[i-1];
            } // end iteration

            weights = w;
        } // end Solution(int[] w)

        public int pickIndex() {
            int left = 0;
            int right = weights.length - 1;
            final int index = random.nextInt(weights[right]) + 1;

            int tmp = Arrays.binarySearch(weights, index);
            if ( tmp < 0 ) {
                tmp = -tmp - 1;
            }
            return tmp;
        } // end int pickIndex()
    } // end class Solution

    /**
     * Runtime: 26 ms
     * Memory Usage: 44.8 MB
     */
    class SolutionSlow {

        private final NavigableMap<Integer, Integer> map = new TreeMap<>();
        private final Random random;
        private int total = 0;

        public SolutionSlow(int[] w) {
            random = new Random();

            for(int i=0; i<w.length; ++i) {
                total += w[i];
                map.put(total, i);
            } // end for(int weight : w)

            //System.out.println(map);
        } // end Solution(int[] w)

        public int pickIndex() {
            final int value = random.nextInt(total) + 1;
            return map.ceilingEntry(value).getValue();
        } // end int pickIndex()
    } // end class SolutionSlow
} // end class Day05
