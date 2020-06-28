package com.jpvr.codechallenges.leetcode.challenge202006.week04;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * COUNT COMPLETE TREE NODES
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Note:
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Example:
 *
 * Input:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * Output: 6
 */
public class Day23 {

    TreeNode root;

    private void setUp() {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
    } // end void setUp()

    @Test
    void should_count_nodes() {
        setUp();
        assertThat(countNodes(root), is(6));
    } // end void should_count_nodes()

    /**
     * Runtime: 0 ms
     * Memory Usage: 42.2 MB
     */
    public int countNodes(TreeNode root) {
        if ( root == null ) {
            return 0;
        }

        TreeNode left = root;
        TreeNode right = root;
        int hLeft = 0;
        int hRight = 0;

        while (left != null) {
            hLeft++;
            left = left.left;
        } // end iteration

        while (right != null) {
            hRight++;
            right = right.right;
        } // end iteration

        if (hLeft == hRight) {
            return (int)Math.pow(2, hLeft) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    } // end int countNodes(TreeNode root)
} // end class Day23