package com.jpvr.codechallenges.leetcode.challenge202006.week03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * H-INDEX II
 *
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer)
 * of a researcher, write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her
 * N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 * - Input: citations = [0,1,3,5,6]
 * - Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
 *              received 0, 1, 3, 5, 6 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note:
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Follow up:
 *
 * This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
 * Could you solve it in logarithmic time complexity?
 *
 * Hint #1
 * - Expected runtime complexity is in O(log n) and the input is sorted.
 */
public class Day18 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[] {3, 4, 5, 8, 10}, 4),
                Arguments.of(new int[] {3, 3, 5, 8, 25}, 3),
                Arguments.of(new int[] {0}, 0),
                Arguments.of(new int[] {1}, 1),
                Arguments.of(new int[] {100}, 1),
                Arguments.of(new int[] {0,0}, 0),
                Arguments.of(new int[] {0, 1, 3, 5, 6}, 3),
                Arguments.of(new int[] {0, 2, 4, 6, 8, 10}, 4)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => citations = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_get_h_index(int[] citations, int expectedOutput) {
        assertThat(hIndexTwo(citations), is(expectedOutput));
    } // end void should_get_h_index(int[] citations, int expectedOutput)

    private int hIndexTwo(int[] citations) {
        return hashIndexHelper(citations, 0);
    }

    private int hashIndexHelper(int[] c, int ans) {
        for(int sz = c.length, i = sz - 1; i>=0; i--) {
            ans = Math.max(ans, Math.min(c[i], sz - i));
        }
        return ans;
    } // end

    /**
     * Runtime: 0 ms
     * Memory Usage: 47 MB
     */
    private int hIndex(int[] citations) {
        final int length = citations.length;
        if ( length == 0 ) {
            return 0;
        }

        int low = 0;
        int high = length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if ( length - mid > citations[mid] ) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } // end iteration

        return length - low;
    } // end int hIndex(int[] citations)
} // end class Day18
