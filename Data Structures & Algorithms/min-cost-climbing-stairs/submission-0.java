class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int a = 0;
        int b = 0;

        for (int i = 1; i < n; i++) {
            int c = b;
            b = Math.min(b + cost[i], a + cost[i - 1]);
            a = c;
        }

        return b;
    }
}
