class Solution {
    private static class CharMap {
        private static final int CHARACTERS = 26;

        private final char[] storage;
        private int size;

        private CharMap() {
            this.storage = new char[CHARACTERS];
            for (int i = 0; i < CHARACTERS; i++) {
                this.storage[i] = 0;
            }
            this.size = 0;
        }

        private int getIndex(char c) {
            return c - 'A';
        }

        public void inc(char c) {
            int index = getIndex(c);
            if (storage[index] == 0) {
                size++;
            }
            storage[index]++;
        }

        public void dec(char c) {
            int index = getIndex(c);
            if (storage[index] == 1) {
                size--;
            }
            storage[index]--;
        }

        public int getMaxFrequency() {
            int maxFrequency = 0;
            for (int i = 0; i < CHARACTERS; i++) {
                maxFrequency = Math.max(maxFrequency, storage[i]);
            }

            return maxFrequency;
        }
    }

    public int characterReplacement(String s, int k) {
        int mx = 0;
        int l = 0;
        int r = 0;
        CharMap charMap = new CharMap();

        for (; r < s.length(); r++) {
            charMap.inc(s.charAt(r));

            while (l < r && r - l + 1 - charMap.getMaxFrequency() > k) {
                charMap.dec(s.charAt(l));
                l++;
            }

            mx = Math.max(mx, r - l + 1);
        }

        return mx;
    }
}
