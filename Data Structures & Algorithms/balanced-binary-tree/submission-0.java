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
    private static record Balance(boolean balance, int height) {}

    public boolean isBalanced(TreeNode root) {
        return balanceHelper(root).balance();
    }

    private Balance balanceHelper(TreeNode root) {
        if (root == null) {
            return new Balance(true, 0);
        }

        Balance leftBalance = balanceHelper(root.left);
        Balance rightBalance = balanceHelper(root.right);

        boolean balance = leftBalance.balance() && rightBalance.balance() 
                && (Math.abs(leftBalance.height() - rightBalance.height()) <= 1);
        
        int height = Math.max(leftBalance.height(), rightBalance.height()) + 1;

        return new Balance(balance, height);
    }
}
