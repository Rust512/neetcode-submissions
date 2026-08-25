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
    public boolean isValidBST(TreeNode root) {
        return validateBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBSTHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        int value = node.val;

        if (value <= min || value >= max) {
            return false;
        }

        return validateBSTHelper(node.left, min, value) && validateBSTHelper(node.right, value, max);
    }
}
