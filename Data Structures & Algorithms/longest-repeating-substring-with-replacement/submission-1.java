class Solution {
    private static class CharCounter {
        private static final int CHARACTERS = 26;
        private final int[] map = new int[CHARACTERS];
        private int maxFrequency = 0;
        private int maxFrequencyIndex = -1;

        private CharCounter() {
        }

        private CharCounter(String s) {
            assert !s.isEmpty();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                assert ch >= 'A' && ch <= 'Z';
                increment(ch);
            }
        }

        private int getCount(char ch) {
            return map[ch - 'A'];
        }

        private void increment(char ch) {
            map[ch - 'A'] += 1;
            if (getCount(ch) > maxFrequency) {
                maxFrequency = getCount(ch);
                maxFrequencyIndex = ch - 'A';
            }
        }

        private int getMaxFrequencyIndex() {
            return maxFrequencyIndex;
        }
    }

    public int characterReplacement(String s, int k) {
        int n = s.length();
        var counter = new CharCounter(s);
        char mostFrequentLetter = (char) (counter.getMaxFrequencyIndex() + 'A');

        int start = 0;
        int end = 0;
        int result = 0;
        int countReplaced = 0;

        do {
            for (;end < n; end++) {
                char ch = s.charAt(end);
                if (ch == mostFrequentLetter) {
                    continue;
                }
                if (countReplaced == k) {
                    break;
                }
                countReplaced++;
            }
            result = Math.max(result, end - start);
            start++;
            for (; start < n; start++) {
                char ch = s.charAt(start);
                if (ch != mostFrequentLetter) {
                    countReplaced--;
                    break;
                }
            }
        } while (end < n);

        return result;
    }
}
