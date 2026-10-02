class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        int start = 0;
        int end = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j) || (!dp[i + 1][j - 1] && j != i + 1)) {
                    continue;
                }

                dp[i][j] = true;
                int length = j - i + 1;
                
                if (length > maxLength) {
                    maxLength = length;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }
}
