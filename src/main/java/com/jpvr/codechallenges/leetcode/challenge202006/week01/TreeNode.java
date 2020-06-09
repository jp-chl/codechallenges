package com.jpvr.codechallenges.leetcode.challenge202006.week01;

// Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static String printPreorder(TreeNode node) {

        if ( node == null ) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(node.val).append(" ");

        sb.append(printPreorder(node.left));

        sb.append(printPreorder(node.right));

        return sb.toString();
    } // end static void printPreorder(TreeNode node)
} // end class TreeNode