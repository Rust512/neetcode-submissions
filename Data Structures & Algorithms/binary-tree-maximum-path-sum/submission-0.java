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
    private static record Result(int nodeMaxPathSum, int maxPathSum){}

    public int maxPathSum(TreeNode root) {
        Result res = helper(root, Integer.MIN_VALUE);

        return res.maxPathSum();
    }

    private Result helper(TreeNode node, int maxPathSumTillNode) {
        if (node == null) {
            return new Result(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        int newMaxPathSumTillNode = Math.max(maxPathSumTillNode, maxPathSumTillNode + node.val);

        Result leftRes = helper(node.left, newMaxPathSumTillNode);
        Result rightRes = helper(node.right, newMaxPathSumTillNode);

        int childNodeMaxPathSum = Math.max(leftRes.nodeMaxPathSum(), rightRes.nodeMaxPathSum());

        int localMax = Math.max(leftRes.nodeMaxPathSum() + rightRes.nodeMaxPathSum() + node.val, newMaxPathSumTillNode + childNodeMaxPathSum);

        int newNodeMaxPathSum = Math.max(node.val, node.val + childNodeMaxPathSum);

        int newMax = Math.max(localMax, Math.max(leftRes.maxPathSum(), rightRes.maxPathSum()));

        return new Result(newNodeMaxPathSum, newMax);
    }
}
