package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SUM ROOT TO LEAF NUMBERS
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 *
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class Day27 {

    TreeNode root;

    @Test
    void should_sum_root_to_leaf_numbers() {
        int expectedOutput = testCase1();
        assertThat(sumNumbers(root), is(expectedOutput));

        expectedOutput = testCase2();
        assertThat(sumNumbers(root), is(expectedOutput));

        expectedOutput = testCase3();
        assertThat(sumNumbers(root), is(expectedOutput));

        expectedOutput = testCase4();
        assertThat(sumNumbers(root), is(expectedOutput));
    } // end void should_sum_root_to_leaf_numbers()

    private int testCase1() {
        root = null;

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        return 25;
    } // end int testCase1()
    private int testCase2() {
        root = null;

        root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        return 1026;
    } // end int testCase2()
    private int testCase3() {
        root = null;

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return 262;
    } // end int testCase3()
    private int testCase4() {
        root = null;

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        return 385;
    } // end int testCase4()

    /**
     * Runtime: 0 ms
     * Memory Usage: 37.1 MB
     */
    private int sumNumbers(TreeNode root) {
        if ( root == null ) {
            return 0;
        }

        return dfs(root, 0);
    } // end int sumNumbers(TreeNode root)

    private int dfs(TreeNode root, int val) {
        if ( root == null ) {
            return 0;
        }

        int result = 0;

        val = val*10 + root.val;

        if ( root.left == null  && root.right == null ) {
            result += val;
        } // end

        result += dfs(root.left, val);
        result += dfs(root.right, val);

        return result;
    } // end int dfs(TreeNode root, int val)
} // end class Day27





















































