class Solution {
    public int longestConsecutive(int[] nums) {
        var set = new HashSet<Integer>();
        for (int num: nums) {
            set.add(num);
        }

        var starters = new ArrayList<Integer>();
        for (int num: nums) {
            if (!set.contains(num - 1)) {
                starters.add(num);
            }
        }

        int maxSize = 0;
        for (int starter: starters) {
            int size = 1;
            while(set.contains(starter + 1)) {
                size++;
                starter++;
            }
            maxSize = Math.max(size, maxSize);
        }

        return maxSize;
    }
}
