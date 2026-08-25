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
    private static class ValidationResult {
        private boolean valid;
        private int min;
        private int max;

        ValidationResult(boolean valid, int min, int max) {
            this.valid = valid;
            this.min = min;
            this.max = max;
        }

        boolean isValid() {
            return valid;
        }

        int getMax() {
            return max;
        }

        int getMin() {
            return min;
        }
    }
    public boolean isValidBST(TreeNode root) {
        ValidationResult result = validateBSTHelper(root);

        return result.isValid();
    }

    private ValidationResult validateBSTHelper(TreeNode node) {
        if (node == null) {
            return new ValidationResult(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        ValidationResult leftResult = validateBSTHelper(node.left);
        ValidationResult rightResult = validateBSTHelper(node.right);

        boolean isValid = leftResult.isValid() && rightResult.isValid() && (node.val > leftResult.getMax()) && (node.val < rightResult.getMin());

        int min = Math.min(node.val, Math.min(leftResult.getMin(), rightResult.getMin()));
        int max = Math.max(node.val, Math.max(leftResult.getMax(), rightResult.getMax()));
        return new ValidationResult(isValid, min, max);
    }
}
