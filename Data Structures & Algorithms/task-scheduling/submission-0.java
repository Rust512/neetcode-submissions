class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];

        for (char letter : tasks) {
            int index = letter - 'A';
            frequencies[index]++;
        }

        int maxFrequency = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int frequency : frequencies) {
            if (frequency == 0) {
                continue;
            }
            minHeap.offer(frequency);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        int totalCycles = 0;

        int subtractedFrequency = 0;
        while (minHeap.size() > n) {
            int minVal = minHeap.peek();
            int size = minHeap.size();
            while (!minHeap.isEmpty() && minHeap.peek() == minVal) {
                minHeap.poll();
            }
            totalCycles += (minVal - subtractedFrequency) * size;
            subtractedFrequency = minVal;
        }

        int maxFrequencyCount = 0;
        for (int frequency : frequencies) {
            if (frequency == maxFrequency) {
                maxFrequencyCount++;
            }
        }

        totalCycles += (maxFrequency - subtractedFrequency - 1) * (n + 1) + maxFrequencyCount;

        return totalCycles;
    }
}
