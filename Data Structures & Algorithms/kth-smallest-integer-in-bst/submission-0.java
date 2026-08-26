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
    private static class Counter {
        private int val;
        private int count;

        Counter(int val, int count) {
            this.val = val;
            this.count = count;
        }

        int getVal() {
            return val;
        }

        int getCount() {
            return count;
        }
    }
    
    public int kthSmallest(TreeNode root, int k) {
        Counter counter = helper(root, k);
        return counter.getVal();
    }

    private Counter helper(TreeNode root, int k) {
        if (root == null) {
            return new Counter(0, 0);
        }

        Counter left = helper(root.left, k);
        
        if (left.getVal() > 0) {
            return left;
        }

        int count = left.getCount() + 1;

        if (count == k) {
            return new Counter(root.val, count);
        }
        
        Counter right = helper(root.right, k - count);

        if (right.getVal() > 0) {
            return right;
        }

        return new Counter(0, count + right.getCount());
    }
}
