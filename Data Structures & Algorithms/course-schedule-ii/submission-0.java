class Solution {

    private static enum Status {
        PENDING,
        IN_PROGRESS,
        DONE,
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        assert numCourses > 0;
        List<List<Integer>> adjacencyList = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (var entry : prerequisites) {
            assert entry[0] < numCourses;
            assert entry[1] < numCourses;
            adjacencyList.get(entry[0]).add(entry[1]);
        }

        Status[] status = new Status[numCourses];
        Arrays.fill(status, Status.PENDING);

        List<Integer> topologicalOrder = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (detectCycle(i, adjacencyList, status, topologicalOrder)) {
                return new int[0];
            }
        }

        return topologicalOrder.stream().mapToInt(Integer::intValue).toArray();
    }

    private static boolean detectCycle(
        int vertex,
        List<List<Integer>> adjacencyList,
        Status[] status,
        List<Integer> order
    ) {
        if (status[vertex] == Status.DONE) {
            return false;
        }

        if (status[vertex] == Status.IN_PROGRESS) {
            return true;
        }

        status[vertex] = Status.IN_PROGRESS;
        for (var neighbor : adjacencyList.get(vertex)) {
            if (detectCycle(neighbor, adjacencyList, status, order)) {
                return true;
            }
        }

        status[vertex] = Status.DONE;

        order.add(vertex);

        return false;
    }
}
