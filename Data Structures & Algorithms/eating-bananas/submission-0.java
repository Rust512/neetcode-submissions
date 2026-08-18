class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int end = getMax(piles);

        int rate = 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int value = getHours(piles, mid);

            if (value == h) {
                return mid;
            }

            if (value < h) {
                rate = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return rate;
    }

    private int getMax(int[] piles) {
        int mx = Integer.MIN_VALUE;

        for (int pile : piles) {
            mx = Math.max(pile, mx);
        }

        return mx;
    }

    private int getHours(int[] piles, int rate) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile / rate) + (pile % rate > 0 ? 1 : 0);
        }

        return hours;
    }
}
