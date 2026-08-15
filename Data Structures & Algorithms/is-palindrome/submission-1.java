class Solution {
    public boolean isPalindrome(String s) {
        char[] charArray = s.toLowerCase().toCharArray();
        int start = 0;
        int end = charArray.length - 1;
        while (start <= end) {
            while (start <= end && isNonAlphaNumeric(charArray[start])) {
                start++;
            }
            if (start == charArray.length) {
                return true;
            }
            while (isNonAlphaNumeric(charArray[end])) {
                end--;
            }
            if (charArray[start++] != charArray[end--]) {
                return false;
            }
        }

        return true;
    }

    private boolean isNonAlphaNumeric(char c) {
        return !isAlphabet(c) && !isDigit(c);
    }

    private boolean isAlphabet(char c) {
        int diff = c - 'a';
        return (diff >= 0 && diff < 26);
    }

    private boolean isDigit(char c) {
        int diff = c - '0';
        return (diff >= 0 && diff < 10);
    }
}
