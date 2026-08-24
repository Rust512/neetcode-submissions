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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        return isEqual(root, subRoot);
    }

    private boolean isEqual(TreeNode node, TreeNode reference) {
        boolean nodeNull = node == null;
        boolean referenceNull = reference == null;

        if (nodeNull && referenceNull) {
            return true;
        }

        if (nodeNull ^ referenceNull) {
            return false;
        }

        if (node.val != reference.val) {
            return false;
        }

        return isEqual(node.left, reference.left) && isEqual(node.right, reference.right);
    }
}
