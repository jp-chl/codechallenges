package com.jpvr.codechallenges.leetcode.challenge202006.week03;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * VALIDATE IP ADDRESS
 *
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 *
 * IPv4 addresses are canonically represented in dot-decimal notation,
 * which consists of four decimal numbers, each ranging from 0 to 255,
 * separated by dots ("."), e.g.,172.16.254.1;
 *
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 * IPv6 addresses are represented as eight groups of four hexadecimal digits,
 * each group representing 16 bits. The groups are separated by colons (":").
 * For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
 * Also, we could omit some leading zeros among four hexadecimal digits and some
 * low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334
 * is also a valid IPv6 address(Omit leading zeros and using upper cases).
 *
 * However, we don't replace a consecutive group of zero value with a single empty group using
 * two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334
 * is an invalid IPv6 address.
 *
 * Besides, extra leading zeros in the IPv6 is also invalid.
 * For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 * Note: You may assume there is no extra space or special characters in the input string.
 *
 * Example 1:
 * - Input: "172.16.254.1"
 * - Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Example 2:
 * - Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * - Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 *
 * Example 3:
 * - Input: "256.256.256.256"
 * - Output: "Neither"
 *
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
public class Day16 {

    private static final String IPV4 = "IPv4";
    private static final String IPV6 = "IPv6";
    private static final String NEITHER = "Neither";

    static Stream<Arguments> testParameters () {
        return Stream.of(
                Arguments.of("172.16.254.1", IPV4),
                Arguments.of("2001:0db8:85a3:0:0:8A2E:0370:7334", IPV6),
                Arguments.of("256.256.256.256", NEITHER)
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => n = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_validate_ip_address(String IP, String expectedOutput) {
        assertThat(validIPAddress(IP), is(expectedOutput));
    } // end void should_validate_ip_address(String IP, String expectedOutput)

    /**
     * Runtime: 8 ms
     * Memory Usage: 40.6 MB
     */
    final Matcher matcherIpV4 = Pattern
            .compile("^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$")
            .matcher("");
    final Matcher matcherIpV6 = Pattern
            .compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$")
            .matcher("");
    private String validIPAddress(String IP) {
        if ( matcherIpV4.reset(IP).matches() ) {
            return "IPv4";
        }
        if ( matcherIpV6.reset(IP).matches() ) {
            return "IPv6";
        }
        return "Neither";
    } // end String validIPAddress(String IP)

    /**
     * Runtime: 1 ms
     * Memory Usage: 37.6 MB
     */
    public String validIPAddressString(String IP) {
        if (IP == null) {
            return "Neither";
        }
        if (isv4(IP)) {
            return "IPv4";
        } else if (isv6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    } // end String validIPAddressString(String IP)

    private boolean isv4(String ip) {
        if (ip.endsWith(".")) {
            return false;
        }
        String[] array = ip.split("\\.");
        if (array.length != 4) {
            return false;
        }
        for (String chunk : array) {
            if (chunk.length() == 0 || chunk.length() != 1 && chunk.charAt(0) == '0') {
                return false;
            }
            if (chunk.contains("+") || chunk.contains("-")) {
                return false;
            }
            try {
                int num = Integer.parseInt(chunk);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch(NumberFormatException e) {
                return false;
            }
        } // end iteration
        return true;
    } // end boolean isv4(String ip)

    private boolean isv6(String ip) {
        if (ip.endsWith(":")) {
            return false;
        }
        String[] array = ip.split(":");
        if (array.length != 8) {
            return false;
        }
        for (String chunk : array) {
            if (chunk.length() < 1 || chunk.length() > 4) {
                return false;
            }
            if (chunk.contains("+") || chunk.contains("-")) {
                return false;
            }
            try {
                Integer.parseInt(chunk, 16);
            } catch(NumberFormatException e) {
                return false;
            }
        } // end iteration
        return true;
    } // end boolean isv6(String ip)

    /** OFFICIAL SOLUTIONS **/
    // Option 1, fails with test case "01.01.01.01"
    public String validIPAddressJavaInetPackage(String IP) {
        try {
            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
        } catch(Exception e) {}
        return "Neither";
    } // end String validIPAddressJavaInetPackage(String IP)

    // Option 2, Regex
    /**
     * Runtime: 10 ms
     * Memory Usage: 39.8 MB
     */
    String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    Pattern pattenIPv4 =
            Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

    String chunkIPv6 = "([0-9a-fA-F]{1,4})";
    Pattern pattenIPv6 =
            Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");
    public String validIPAddressRegex(String IP) {
        if (pattenIPv4.matcher(IP).matches()) return "IPv4";
        return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
    }

    // Option 3, String handling
    /**
     * Runtime: 3 ms
     * Memory Usage: 37.5 MB
     */
    public String validIPAddressDivideAndConquer(String IP) {
        if (IP.chars().filter(ch -> ch == '.').count() == 3) {
            return validateIPv4(IP);
        }
        else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
            return validateIPv6(IP);
        }
        else return "Neither";
    } // end String validIPAddressDivideAndConquer(String IP)

    private String validateIPv4(String IP) {
        String[] nums = IP.split("\\.", -1);
        for (String x : nums) {
            // Validate integer in range (0, 255):
            // 1. length of chunk is between 1 and 3
            if (x.length() == 0 || x.length() > 3) return "Neither";
            // 2. no extra leading zeros
            if (x.charAt(0) == '0' && x.length() != 1) return "Neither";
            // 3. only digits are allowed
            for (char ch : x.toCharArray()) {
                if (! Character.isDigit(ch)) return "Neither";
            }
            // 4. less than 255
            if (Integer.parseInt(x) > 255) return "Neither";
        }
        return "IPv4";
    } // end String validateIPv4(String IP)

    private String validateIPv6(String IP) {
        String[] nums = IP.split(":", -1);
        String hexdigits = "0123456789abcdefABCDEF";
        for (String x : nums) {
            // Validate hexadecimal in range (0, 2**16):
            // 1. at least one and not more than 4 hexdigits in one chunk
            if (x.length() == 0 || x.length() > 4) return "Neither";
            // 2. only hexdigits are allowed: 0-9, a-f, A-F
            for (Character ch : x.toCharArray()) {
                if (hexdigits.indexOf(ch) == -1) return "Neither";
            }
        }
        return "IPv6";
    } // end String validateIPv6(String IP)
} // end class Day16