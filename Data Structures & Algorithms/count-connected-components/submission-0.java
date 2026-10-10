class Solution {
    private List<List<Integer>> adjList;
    private boolean[] visited;

    public int countComponents(int n, int[][] edges) {
        visited = new boolean[n];
        adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (var edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        int componentCount = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            explore(i);
            componentCount++;
        }

        return componentCount;
    }

    private void explore(int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (int neighbour : adjList.get(node)) {
            explore(neighbour);
        }
    }
}
