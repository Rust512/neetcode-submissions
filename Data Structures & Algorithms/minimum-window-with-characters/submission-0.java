class Solution {
    public String minWindow(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();

        if (tLength > sLength) {
            return "";
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> sMap = new HashMap<>();

        int l = 0;
        String minWindow = s;

        for (int r = 0; r < sLength; r++) {
            char current = s.charAt(r);
            sMap.put(current, sMap.getOrDefault(current, 0) + 1);
            if (!contains(tMap, sMap)) {
                continue;
            }

            while (l < r && (!tMap.containsKey(s.charAt(l)) || tMap.get(s.charAt(l)) < sMap.get(s.charAt(l)))) {
                sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
                l++;
            }

            if (r - l + 1 < minWindow.length()) {
                minWindow = s.substring(l, r + 1);
            }

            sMap.put(s.charAt(l), sMap.get(s.charAt(l)) - 1);
            l++;
        }

        return minWindow;
    }

    private boolean contains(Map<Character, Integer> contained, Map<Character, Integer> container) {
        return contained.entrySet()
                .stream()
                .allMatch(entry -> container.containsKey(entry.getKey()) && container.get(entry.getKey()) >= entry.getValue());
    }
}
