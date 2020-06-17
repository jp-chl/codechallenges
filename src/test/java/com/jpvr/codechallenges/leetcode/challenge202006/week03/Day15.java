package com.jpvr.codechallenges.leetcode.challenge202006.week03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SEARCH IN A BINARY SEARCH TREE
 *
 * Solution
 * Given the root node of a binary search tree (BST) and a value.
 * You need to find the node in the BST that the node's value equals the given value.
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 *
 * For example,
 *
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * And the value to search: 2
 * You should return this subtree:
 *
 *       2
 *      / \
 *     1   3
 * In the example above, if we want to search the value 5,
 * since there is no node with value 5, we should return NULL.
 *
 * Note that an empty tree is represented by NULL, therefore you would
 * ee the expected output (serialized tree format) as [], not null.
 */
public class Day15 {

    TreeNode root;

    @BeforeEach
    private void setUp() {
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
    } // end void setUp()

    @Test
    void should_find_node_recursively() {
        assertThat(searchBSTRecursive(root, 2), is(root.left));
    } // end void should_find_node_recursively()

    @Test
    void should_not_find_node_recursively() {
        Assertions.assertNull(searchBSTRecursive(root, 8));
    } // end void should_not_find_node_recursively()

    @Test
    void should_find_node_iteratively() {
        assertThat(searchBSTIterative(root, 2), is(root.left));
    } // end void should_find_node_iteratively()

    @Test
    void should_not_find_node_iteratively() {
        Assertions.assertNull(searchBSTIterative(root, 8));
    } // end should_not_find_node_iteratively()

    /**
     * Runtime: 0 ms
     * Memory: 39.5 MB
     */
    public TreeNode searchBSTRecursive(TreeNode root, int val) {
        // Recursive approach
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        if (root.val > val) {
            return searchBSTRecursive(root.left, val);
        }

        return searchBSTRecursive(root.right, val);
    } // end TreeNode searchBSTRecursive(TreeNode root, int val)

    /**
     * Runtime: 0 ms
     * Memory: 40 MB
     */
    public TreeNode searchBSTIterative(TreeNode root, int val) {
        // Iterative approach
        TreeNode current = root;

        while(current != null) {

            if ( current.val == val ) {
                return current;
            }

            current = (current.val > val) ? current.left : current.right;
        } // end iteration

        return current;
    } // end TreeNode searchBSTIterative(TreeNode root, int val)
} // end class Day15