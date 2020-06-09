package com.jpvr.codechallenges.leetcode.challenge202006.week01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * INVERT BINARY TREE
 *
 * Invert a binary tree.
 *
 * Example:
 *
 * Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * Output:
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * - Google: 90% of our engineers use the software you wrote (Homebrew),
 *   but you can’t invert a binary tree on a whiteboard so f*** off.
 */
public class Day01 {

    static Stream<Arguments> testParameters () {

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        //String treeAsString = TreeNode.printPreorder(root);

        return Stream.of(
                Arguments.of( root, "4 7 9 6 2 3 1 ")
        ); } // end static Stream<Arguments> testParameters ()

    @ParameterizedTest(name = "{index} => input = {0}, expected = {1}")
    @MethodSource("testParameters")
    public void should_invert_binary_tree(TreeNode root, String expectedOutput) {
        assertThat(TreeNode.printPreorder(invertTree(root)), is(expectedOutput));
    } // end void should_invert_binary_tree(TreeNode root, String expectedOutput)

    /**
     * Runtime: 0 ms
     * Memory Usage: 37 MB
     */
    public TreeNode invertTree(TreeNode root) {

        return reverse(root);
    } // end TreeNode invertTree(TreeNode root)

    private TreeNode reverse(TreeNode root) {

        if ( root == null ) {
            return null;
        }

        final TreeNode tmp = root.left;
        root.left = reverse(root.right);
        root.right = reverse(tmp);

        return root;
    } // end TreeNode reverse(TreeNode root)

    private boolean isSymmetric(TreeNode root) {
        return isSymmetricHelper(root.left, root.right);
    } // end boolean isSymmetric(TreeNode root)

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {

        if ( left == null  &&  right == null ) {
            return true;
        }

        if ( left == null  ||  right == null ) {
            return false;
        }

        return isSymmetricHelper(left.left, left.right) &&
                isSymmetricHelper(right.left, right.right);
    } // end boolean isSymmetricHelper(TreeNode left, TreeNode right)
} // end class Day01