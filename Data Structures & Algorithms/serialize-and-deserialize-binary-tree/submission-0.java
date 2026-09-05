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

public class Codec {
    private static final String DELIMITER = " ";
    private static final String NULL = "null";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> values = new ArrayList<>();
        Deque<TreeNode> container = new LinkedList<>();
        container.offer(root);

        while (!container.isEmpty()) {
            TreeNode removed = container.poll();
            if (removed == null) {
                TreeNode topNode = container.peekLast();
                if (topNode != null) {
                    values.add(NULL);
                }
                continue;
            }

            values.add(String.valueOf(removed.val));

            container.offer(removed.left);
            container.offer(removed.right);
        }

        return values.stream()
                .collect(Collectors.joining(DELIMITER));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] values = data.split(DELIMITER);
        int totalValues = values.length;
        Queue<TreeNode> container = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        container.offer(root);

        int index = 1;

        while (!container.isEmpty()) {
            TreeNode frontNode = container.poll();
            if (frontNode == null) {
                continue;
            }

            if (index < totalValues) {
                String leftValue = values[index];
                index++;
                if (!Objects.equals(leftValue, NULL)) {
                    TreeNode leftNode = new TreeNode(
                        Integer.parseInt(leftValue)
                    );
                    frontNode.left = leftNode;
                    container.offer(leftNode);
                }
            }

            if (index < totalValues) {
                String rightValue = values[index];
                index++;
                if (!Objects.equals(rightValue, NULL)) {
                    TreeNode rightNode = new TreeNode(
                        Integer.parseInt(rightValue)
                    );
                    frontNode.right = rightNode;
                    container.offer(rightNode);
                }
            }
        }

        return root;
    }
}
