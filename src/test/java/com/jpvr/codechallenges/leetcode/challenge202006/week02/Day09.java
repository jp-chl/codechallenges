package com.jpvr.codechallenges.leetcode.challenge202006.week02;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * IS SUBSEQUENCE
 *
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * A subsequence of a string is a new string which is formed from
 * the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters.
 * (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you
 * want to check one by one to see if T has its subsequence.
 * In this scenario, how would you change your code?
 *
 * Example 1:
 * - Input: s = "abc", t = "ahbgdc"
 * - Output: true
 *
 * Example 2:
 * - Input: s = "axc", t = "ahbgdc"
 * - Output: false
 *
 * Constraints:
 * - 0 <= s.length <= 100
 * - 0 <= t.length <= 10^4
 * - Both strings consists only of lowercase characters.
 */
public class Day09 {
    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of("a", "a", true),
                Arguments.of("a", "ab", true),
                Arguments.of("a", "acf", true),
                Arguments.of("a", "cde", false),
                Arguments.of("ab", "adefbsgi", true),
                Arguments.of("abcd", "ahbgdc", false),
                Arguments.of("abc", "ahbgdc", true),
                Arguments.of("axc", "ahbgdc", false),

                Arguments.of("", "", true),
                Arguments.of("", "qsndsin", true),
                Arguments.of("a", "", false),
                Arguments.of("a", "arnsoubnuebcbweriububwnefnbeicnwcnwcnwcnwoicnwoeicnweoicnweoicnweoicnweocinweoicnweicnwicnwoicnweicn", true),
                Arguments.of("za", "asnsanasnasoinasoinasoinsaznaoisnasinsdaoidnaidnaoidnasoinoiniqwoiqpwojwqowjqa", true),
                Arguments.of("ajm", "aasmiamnsiassanoansdiosajakslamsdlasmdlasmlasmlsamda", true),
                Arguments.of("ajm", "aasdaodskaodkasodkaokdoakdoaskdsoakmkasopdkaposkaspodkaodkadokaodkaspoksaj", false),
                Arguments.of("aasjdiajsdoiqjodi", "aasjdiajsdoiqjodi", true),
                Arguments.of("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz", "abcassafafadsffdefghijklmnopqwwqqwqwqrstuvwmmmxyzabcllldefghijklmsssnopqrstuveeewxyzabcdefghijklmnofffpqrstuvwxyoooz", true)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_validate_is_subsequence(String s, String t, boolean expectedOutput) {

        assertThat(isSubsequence(s, t), is(expectedOutput));
    } // end void should_validate_is_subsequence(String s, String t, boolean expectedOutput)

    /**
     * Runtime: 1 ms
     * Memory Usage: 39.1 MB
     */
    public boolean isSubsequence(String s, String t) {
        if ( s == null  ||  t == null  ) {
            return false;
        }

        final int sLength = s.length();
        if ( sLength == 0 ) {
            return true;
        }

        int sCounter = sLength;
        int sPointer = sLength-1;
        int tPointer = t.length()-1;

        while(tPointer >= 0  &&  sPointer >= 0) {

            if ( s.charAt(sPointer) == t.charAt(tPointer) ) {
                sPointer--;
                sCounter--;
            }

            tPointer--;
        } // end iteration

        return (sCounter == 0);
    } // end boolean isSubsequence(String s, String t)

} // end class Day09