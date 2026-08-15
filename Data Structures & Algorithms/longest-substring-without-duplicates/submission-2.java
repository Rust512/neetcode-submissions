class Solution {
    private static class CharMap {
        private static final int CHARACTERS = 128;
        private final int[] map = new int[CHARACTERS];
        private int size = 0;

        private CharMap() {
            Arrays.fill(map, -1);
        }

        private int getSize() {
            return size;
        }

        private boolean contains(char ch) {
            return map[ch] != -1;
        }

        private void put(char ch, int i) {
            if (map[ch] == -1) {
                size++;
            }
            map[ch] = i;
        }

        private int get(char ch) {
            return map[ch];
        }

        private void remove(char ch) {
            if (map[ch] != -1) {
                size--;
            }
            map[ch] = -1;
        }
    }

    private void clearTillNewStart(String s, int start, int newStart, CharMap charMap) {
        for (int i = start; i < newStart; i++) {
            var ch = s.charAt(i);
            charMap.remove(ch);
        }
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        var charMap = new CharMap();

        int start = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            var ch = s.charAt(i);
            if (!charMap.contains(ch)) {
                charMap.put(ch, i);
                continue;
            }
            int newStart = charMap.get(ch) + 1;
            maxLength = Math.max(maxLength, i - start);
            clearTillNewStart(s, start, newStart, charMap);
            start = newStart;
            charMap.put(ch, i);
        }

        maxLength = Math.max(maxLength, n - start);

        return maxLength;
    }
}
