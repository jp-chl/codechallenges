package com.jpvr.codechallenges.leetcode.challenge202006.week03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * DUNGEON GAME
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially
 * positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers)
 * upon entering these rooms; other rooms are either empty (0's) or contain magic
 * orbs that increase the knight's health (positive integers).
 *
 * In order to reach the princess as quickly as possible,
 * the knight decides to move only rightward or downward in each step.
 *
 *
 *
 * Write a function to determine the knight's minimum initial health
 * so that he is able to rescue the princess.
 *
 * For example, given the dungeon below, the initial health of the knight must be
 * at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 * ------------------------------
 *    -2 (K)    |   -3  |   3
 * ------------------------------
 *       -5     |  -10  |   1
 * ------------------------------
 *       10     |   30  |  -5 (P)
 * ------------------------------
 *
 *
 * Note:
 * - The knight's health has no upper bound.
 * - Any room can contain threats or power-ups, even the first room the knight
 *   enters and the bottom-right room where the princess is imprisoned.
 */
public class Day21 {

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(new int[][] {
                        {-2, -3, 3},
                        {-5, -10, 1},
                        {10, -30, -5}}, 7),
                Arguments.of(new int[][] {{0}}, 1),
                Arguments.of(new int[][] {{-200}}, 201),
                Arguments.of(new int[][] {{100}}, 1),
                Arguments.of(new int[][] {{0, -3}}, 4),
                Arguments.of(new int[][] {{0, 0}}, 1),
                Arguments.of(new int[][] {{-3, 5}}, 4),
                Arguments.of(new int[][] {{-1, 1}}, 2)

        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => dungeon = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_calculate_minimum_hp(int[][] dungeon, int expectedOutput) {
        assertThat(calculateMinimumHP(dungeon), is(expectedOutput));
    } // end void should_calculate_minimum_hp(int[][] dungeon, int expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 38.7 MB
     */
    public int calculateMinimumHP(int[][] dungeon) {

        final int rows = dungeon.length;
        final int cols = dungeon[0].length;

        int[][] result = new int[rows][cols];
        result[rows-1][cols-1] = dungeon[rows-1][cols-1] > 0 ? 1 : (1 - dungeon[rows-1][cols-1]);

        for(int i = rows-2; i>= 0; --i) {
            result[i][cols-1] = Math.max(result[i+1][cols-1] - dungeon[i][cols-1], 1);
        } // end

        for(int j = cols-2; j>= 0; --j) {
            result[rows-1][j] = Math.max(result[rows-1][j+1] - dungeon[rows-1][j], 1);
        } // end

        for(int i = rows-2; i>= 0; --i) {
            for(int j = cols-2; j>= 0; --j) {
                result[i][j] = Math.max(Math.min(result[i+1][j], result[i][j+1]) - dungeon[i][j], 1);
            } // end
        } // end

        return result[0][0];
    } // end int calculateMinimumHP(int[][] dungeon)
} // end class Day21
