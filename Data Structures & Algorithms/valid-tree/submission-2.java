class Solution {
    private List<List<Integer>> adjList;
    private boolean[] visited;

    public boolean validTree(int n, int[][] edges) {
        if (n == 1) {
            return true;
        }

        if (edges.length != n - 1) {
            return false;
        }

        visited = new boolean[n];

        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (var edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
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
