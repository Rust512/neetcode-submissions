class Solution {
    private static record Point(int x, int y) {
        int distanceFromOrigin() {
            return x * x + y * y;
        }
    }
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> container = new PriorityQueue<>(Comparator.comparing(Point::distanceFromOrigin));

        for (int[] point : points) {
            container.offer(new Point(point[0], point[1]));
        }

        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++) {
            Point p = container.poll();
            result[i][0] = p.x();
            result[i][1] = p.y();
        }

        return result;
    }
}
