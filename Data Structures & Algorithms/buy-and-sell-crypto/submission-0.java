class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy = 0;
        int maxProfit = 0;

        for (int i = 1; i < n; i++) {
            if (prices[i] < prices[buy]) {
                buy = i;
                continue;
            }
            maxProfit = Math.max(prices[i] - prices[buy], maxProfit);
        }

        return maxProfit;
    }
}
