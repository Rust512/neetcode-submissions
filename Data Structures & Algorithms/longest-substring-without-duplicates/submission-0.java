class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int start = 0;
        int[] charMap = new int[128];
        int max = 1;

        for (int i = 0; i < n; i++) {
            char letter = s.charAt(i);
            if (charMap[letter] != 0) {
                max = Math.max(max, i - start - 1);
                start = charMap[letter] + 1;
            }
            if (charMap[letter] >= start) {
                charMap[letter] = i;
            }
        }

        max = Math.max(max, n - 1 - start);

        return max;
    }
}
