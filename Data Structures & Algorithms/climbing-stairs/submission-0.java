class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return n; 
        }
        
        int a = 1;
        int b = 1;

        for (int i = 1; i < n; i++) {
            b = b + a;
            a = b - a;
        }

        return b;
    }
}
