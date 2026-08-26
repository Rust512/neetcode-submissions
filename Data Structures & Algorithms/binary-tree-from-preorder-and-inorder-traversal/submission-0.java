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
    private int[] pre;
    private int[] in;
    Map<Integer, Integer> inMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        in = inorder;
        pre = preorder;
        inMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }
        return helper(0, n - 1, 0, n - 1);
    }

    private TreeNode helper(int inStart, int inEnd, int preStart, int preEnd) {
            if (inStart > inEnd || preStart > preEnd) {
                return null;
            }

            int root = pre[preStart];
            int rootIndex = inMap.get(root);
            int leftSubTreeCount = rootIndex - inStart;

            TreeNode leftSubTree = helper(inStart, rootIndex - 1, preStart + 1, preStart + leftSubTreeCount);
            TreeNode rightSubTree = helper(rootIndex + 1, inEnd, preStart + leftSubTreeCount + 1, preEnd);

            return new TreeNode(root, leftSubTree, rightSubTree);
        }
}
