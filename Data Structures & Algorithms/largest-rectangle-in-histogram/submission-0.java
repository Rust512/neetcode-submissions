class Solution {
    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int[] leftLimit = new int[size];
        int[] rightLimit = new int[size];
        Stack<Integer> container = new Stack<>();
        
        for (int i = 0; i < size; i++) {
            while (!container.isEmpty() && heights[i] <= heights[container.peek()]) {
                int removed = container.pop();
                leftLimit[removed] = container.isEmpty() ? -1 : container.peek();
            }
            container.push(i);
        }

        while (!container.isEmpty()) {
            int removed = container.pop();
            leftLimit[removed] = container.isEmpty() ? -1 : container.peek();
        }

        for (int i = size - 1; i >= 0; i--) {
            while (!container.isEmpty() && heights[i] <= heights[container.peek()]) {
                int removed = container.pop();
                rightLimit[removed] = container.isEmpty() ? size : container.peek();
            }
            container.push(i);
        }

        while (!container.isEmpty()) {
            int removed = container.pop();
            rightLimit[removed] = container.isEmpty() ? size : container.peek();
        }

        int maxArea = 0;

        for (int i = 0; i < size; i++) {
            maxArea = Math.max(maxArea, heights[i] * (rightLimit[i] - leftLimit[i] - 1));
        }

        return maxArea;
    }
}
