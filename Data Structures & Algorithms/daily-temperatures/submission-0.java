class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> container = new Stack<>();
        int size = temperatures.length;
        int[] result = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            int temperature = temperatures[i];

            while (!container.empty() && temperatures[container.peek()] < temperature) {
                int oldTop = container.pop();
                result[oldTop] = container.empty() ? 0 : container.peek() - oldTop;
            }

            container.push(i);
        }

        while (container.size() > 1) {
            int oldTop = container.pop();
            result[oldTop] = container.peek() - oldTop;
        }

        result[container.peek()] = 0;

        return result;
    }
}
