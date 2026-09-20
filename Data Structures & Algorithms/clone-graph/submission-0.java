/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        int startVal = node.val;
        Map<Integer, Node> nodeMap = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        constructClone(node, nodeMap, visited);
        return nodeMap.get(startVal);
    }

    private void constructClone(Node node, Map<Integer, Node> map, Map<Integer, Boolean> visited) {
        int nodeVal = node.val;
        if (Boolean.TRUE.equals(visited.get(nodeVal))) {
            return;
        }

        Node cloned = map.computeIfAbsent(nodeVal, val -> new Node(val));
        visited.put(nodeVal, true);

        for (Node neighbor : node.neighbors) {
            constructClone(neighbor, map, visited);
            cloned.neighbors.add(map.get(neighbor.val));
        }
    }
}