class Solution {
    private Map<Integer, List<Integer>> adjList;
    private boolean[] visited;

    public boolean validTree(int n, int[][] edges) {
        if (n == 1) {
            return true;
        }

        if (edges.length != n - 1) {
            return false;
        }

        visited = new boolean[n];

        adjList = new HashMap<>();
        for (var edge : edges) {
            adjList.merge(edge[0], new ArrayList<>(List.of(edge[1])), (oldVal, newVal) -> {
                oldVal.addAll(newVal);
                return oldVal;
            });
            adjList.merge(edge[1], new ArrayList<>(List.of(edge[0])), (oldVal, newVal) -> {
                oldVal.addAll(newVal);
                return oldVal;
            });
        }

        dfs(0);

        for (boolean state : visited) {
            if (!state) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int neighbour : adjList.get(node)) {
            dfs(neighbour);
        }
    }
}
