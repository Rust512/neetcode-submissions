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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> container = new LinkedList<>();
        container.add(root);

        while(!container.isEmpty()) {
            int nodes = container.size();
            while (nodes != 0) {
                TreeNode front = container.remove();

                TreeNode left = front.left;
                TreeNode right = front.right;

                if (left != null) {
                    container.add(left);
                }

                if (right != null) {
                    container.add(right);
                }

                if (nodes == 1) {
                    result.add(front.val);
                }
                nodes--;
            }
        }

        return result;
    }
}
