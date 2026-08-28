class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> container = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            container.offer(stone);
        }

        while (container.size() > 1) {
            int stone1 = container.poll();
            int stone2 = container.poll();

            int diff = Math.abs(stone1 - stone2);
            if (diff != 0) {
                container.offer(diff);
            }
        }

        return (container.isEmpty()) ? 0 : container.peek();
    }
}
