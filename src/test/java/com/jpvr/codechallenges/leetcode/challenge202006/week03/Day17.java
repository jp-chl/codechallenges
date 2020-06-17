package com.jpvr.codechallenges.leetcode.challenge202006.week03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SURROUNDED REGIONS
 *
 * Given a 2D board containing 'X' and 'O' (the letter O),
 * capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 *
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 *
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * Explanation:
 *
 * Surrounded regions shouldn’t be on the border,
 * which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O'
 * on the border will be flipped to 'X'. Two cells are connected
 * if they are adjacent cells connected horizontally or vertically.
 */
public class Day17 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of(
                        new char[][] {
                                {'O','X','X','O','X'},
                                {'X','O','O','X','O'},
                                {'X','O','X','O','X'},
                                {'O','X','O','O','O'},
                                {'X','X','O','X','O'}},
                        new char[][] {
                                {'O','X','X','O','X'},
                                {'X','X','X','X','O'},
                                {'X','X','X','O','X'},
                                {'O','X','O','O','O'},
                                {'X','X','O','X','O'}}),
                Arguments.of(
                        new char[][] {
                                {'X','X','X','X'},
                                {'X','O','O','X'},
                                {'X','X','O','X'},
                                {'X','O','X','X'}},
                        new char[][] {
                                {'X','X','X','X'},
                                {'X','X','X','X'},
                                {'X','X','X','X'},
                                {'X','O','X','X'}}),
                Arguments.of(
                        new char[][] {
                                {'X','X','O','X'},
                                {'O','O','O','X'},
                                {'X','X','X','X'}},
                        new char[][] {
                                {'X','X','O','X'},
                                {'O','O','O','X'},
                                {'X','X','X','X'}}),
                Arguments.of(
                        new char[][] {
                                {'X','X'},
                                {'O','O'},
                                {'X','X'}},
                        new char[][] {
                                {'X','X'},
                                {'O','O'},
                                {'X','X'}}),
                Arguments.of(
                        new char[][] {
                                {'X','X','X','X','X','X'},
                                {'X','X','X','X','X','X'},
                                {'O','O','X','O','X','O'},
                                {'X','X','X','X','X','X'}},
                        new char[][] {
                                {'X','X','X','X','X','X'},
                                {'X','X','X','X','X','X'},
                                {'O','O','X','X','X','O'},
                                {'X','X','X','X','X','X'}})
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => board = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_capture_surrounded_regions(char[][] board, char[][] expectedOutput) {
        final char[][] solvedBoard = Arrays.copyOfRange(board, 0, board.length);
        solve(solvedBoard);
        assertThat(solvedBoard, is(expectedOutput));
    } // end void should_validate_ip_address(String IP, String expectedOutput)

    static final char LETTER_O = 'O';
    static final char LETTER_X = 'X';
    static final char LETTER_TEMP = 'T';
    boolean isInBoundaries = false;

    /**
     * Runtime: 2 ms
     * Memory Usage: 41.5 MB
     */
    private void dfs(char[][] board, int i, int j) {
        isInBoundaries = (i>=0 && i<board.length && j>=0 && j<board[0].length);

        if ( isInBoundaries && board[i][j] == LETTER_O ) {
            board[i][j] = LETTER_TEMP;

            dfs(board, i+1, j);
            dfs(board, i-1, j);
            dfs(board, i, j+1);
            dfs(board, i, j-1);
        } // end if
    } // end void dfs(char[][] board, int i, int j)

    private void solve(char[][] board) {
        final int rows = board.length;
        if ( rows <= 2 ) { return; }

        final int cols = board[0].length;
        if ( cols  <= 2 ) { return; }

        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                final boolean isOnBorder = (i == 0 || i == rows - 1 || j == 0 || j == cols - 1);
                if ( board[i][j] == LETTER_O && isOnBorder) {
                    dfs(board, i, j);
                }
            } // end cols iteration
        } // end rows iteration

        for(int i=0; i<rows; ++i) {
            for(int j=0; j<cols; ++j) {
                switch (board[i][j]) {
                    case LETTER_O:
                        board[i][j] = LETTER_X;
                        continue;
                    case LETTER_TEMP:
                        board[i][j] = LETTER_O;
                } // end switch
            } // end cols iteration
        } // end rows iteration

    } // end void solve(char[][] board)
} // end class Day17