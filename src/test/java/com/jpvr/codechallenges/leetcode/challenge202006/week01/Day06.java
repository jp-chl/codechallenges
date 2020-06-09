package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * QUEUE RECONSTRUCTION BY HEIGHT
 *
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of
 * this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 * Input:
 * - [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * Output:
 * - [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class Day06 {

    static Stream<Arguments> testParameters () { return Stream.of(
            Arguments.of( new int[][] {{1,0},{2,0}}, new int[][] {{1,0},{2,0}}),

            Arguments.of( new int[][] {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}},
                    new int[][] {{5,0}, {7,0}, {5,2}, {6,1}, {4,4}, {7,1}}),

            Arguments.of( new int[][] {{6,0}, {5,0}, {4,0}, {3,2}, {2,2}, {1,4}},
                    new int[][] {{4,0}, {5,0}, {2,2}, {3,2}, {1,4}, {6,0}}),

            Arguments.of( new int[][] {{9,0}, {7,0}, {1,9}, {3,0}, {2,7}, {5,3}, {6,0}, {3,4}, {6,2}, {5,2}},
                    new int[][] {{3,0}, {6,0}, {7,0}, {5,2}, {3,4}, {5,3}, {6,2}, {2,7}, {9,0}, {1,9}})
    ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => input = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_reconstruct_queue(int[][] input, int[][] expectedOutput) {
        assertThat(reconstructQueue(input), is(expectedOutput));
    } // end void int[][](int[][] input, int[][] expectedOutput)

    /**
     * Runtime: 8 ms
     * Memory Usage: 40.5 MB
     */
    public int[][] reconstructQueue(int[][] people) {

        List<int[]> result = new ArrayList<>();

        Arrays.sort(people, (a, b) -> {
            return (a[0] == b[0]) ? (a[1] - b[1]) : (b[0] - a[0]);
        });

        for(int[] x : people) {
            result.add(x[1], x);
        }

        return result.toArray(new int[people.length][2]);
    } // end int[][] reconstructQueue(int[][] people)
} // end class Day06