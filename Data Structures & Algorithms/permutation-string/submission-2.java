public class Solution {

    private static class PermutationParser {

        private final Map<Character, Integer> counter;

        private PermutationParser() {
            counter = new TreeMap<>();
        }

        public void inc(char c) {
            int count = counter.getOrDefault(c, 0);
            counter.put(c, count + 1);
        }

        public void dec(char c) {
            int count = counter.getOrDefault(c, 0);
            if (count == 0) {
                return;
            }
            counter.put(c, count - 1);
        }

        public String getSortedString() {
            return counter.entrySet()
                    .stream()
                    .map(entry -> String.valueOf(entry.getKey()).repeat(entry.getValue()))
                    .collect(Collectors.joining());
        }

        public void clear() {
            counter.clear();
        }
    }

    public boolean checkInclusion(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 > l2) {
            return false;
        }

        PermutationParser parser = new PermutationParser();
        for (char c : s1.toCharArray()) {
            parser.inc(c);
        }
        String sortedS1 = parser.getSortedString();
        parser.clear();

        for (int i = 0; i < l1; i++) {
            parser.inc(s2.charAt(i));
        }

        for (int i = l1; i < l2; i++) {
            String sortedSubset = parser.getSortedString();
            if (sortedSubset.equals(sortedS1)) {
                return true;
            }
            parser.inc(s2.charAt(i));
            parser.dec(s2.charAt(i - l1));
        }

        String sortedSubset = parser.getSortedString();

        return sortedSubset.equals(sortedS1);
    }
}
