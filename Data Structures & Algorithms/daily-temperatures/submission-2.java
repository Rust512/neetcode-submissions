class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> container = new Stack<>();
        int size = temperatures.length;
        int[] result = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            while (!container.isEmpty() && temperatures[i] >= temperatures[container.peek()]) {
                int removed = container.pop();
                result[removed] = container.isEmpty() ? 0 : container.peek() - removed;
            }

            container.push(i);
        }

        while (!container.isEmpty()) {
            int removed = container.pop();
            result[removed] = container.isEmpty() ? 0 : container.peek() - removed;
        }

        return result;
    }
}
