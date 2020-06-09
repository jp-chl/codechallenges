package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * REVERSE STRING
 *
 * Write a function that reverses a string.
 * The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array,
 * you must do this by modifying the input array
 * in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 * Example 1:
 * - Input: ["h","e","l","l","o"]
 * - Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * - Input: ["H","a","n","n","a","h"]
 * - Output: ["h","a","n","n","a","H"]
 */
public class Day04 {

    static Stream<Arguments> testParameters () { return Stream.of(
            Arguments.of( new char[] {},
                    new char[] {}),
            Arguments.of( new char[] {'h'},
                    new char[] {'h'}),
            Arguments.of( new char[] {'h','e'},
                    new char[] {'e','h'}),
            Arguments.of( new char[] {'h','e','l'},
                    new char[] {'l','e','h'}),
            Arguments.of( new char[] {'h','e','l','t'},
                    new char[] {'t','l','e','h'}),
            Arguments.of( new char[] {'h','e','l','l','o'},
                    new char[] {'o','l','l','e','h'}),
            Arguments.of( new char[] {'H','a','n','n','a','h'},
                    new char[] {'h','a','n','n','a','H'})
    ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => input = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_reverse_string(char[] input, char[] expectedOutput) {

        char[] reversedInput = reverse(input);
        assertThat(reversedInput, is(expectedOutput));

        reverseString(input);
        assertThat(input, is(expectedOutput));
    } // end void should_reverse_string(String input, String expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 46.4 MB
     */
    public void reverseString(char[] s) {
        if ( s == null )
            throw new IllegalArgumentException();

        int start = 0;
        int end = s.length - 1;

        while( start < end ) {
            //swapCharacters(s, start, end);
            final char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            start++;
            end--;
        } // end swapping iteration

    } // end void reverseString(char[] s)

    private void swapCharacters(char[] s, int i, int j) {
        final char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    } // end void swapCharacters(char[] s, int i, int j)

    public char[] reverse(char[] s) {

        final int length = s.length;

        if ( length <= 1 ) {
            return s;
        }

        final char lastChar = s[length - 1];
        final char[] restOfTheArray = Arrays.copyOfRange(s, 0, length - 1);

        final String resultAsString = lastChar + new String(reverse(restOfTheArray));

        return resultAsString.toCharArray();
    } // end void r(char[] s)
} // ed class Day04

