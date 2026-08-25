/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int goodNodes(TreeNode root) {
        int max = Integer.MIN_VALUE;
        return trackMaximumUpdates(root, max);
    }

    private int trackMaximumUpdates(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }

        int newMax = Math.max(node.val, max);
        return (node.val >= max ? 1 : 0) + trackMaximumUpdates(node.left, newMax) + trackMaximumUpdates(node.right, newMax);
    }
}
