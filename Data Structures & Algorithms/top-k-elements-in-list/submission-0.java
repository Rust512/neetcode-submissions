class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        var frequencyMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (frequencyMap.containsKey(num)) {
                frequencyMap.put(num, frequencyMap.get(num) + 1);
            } else {
                frequencyMap.put(num, 1);
            }
        }

        var maxHeap = new PriorityQueue<Map.Entry<Integer, Integer>>((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        frequencyMap.entrySet().forEach(maxHeap::add);

        if (maxHeap.peek() == null) {
            return new int[0];
        }

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            if (maxHeap.peek() == null) {
                break;
            }
            result[i] = maxHeap.peek().getKey();
            maxHeap.remove();
        }

        return result;
    }
}
