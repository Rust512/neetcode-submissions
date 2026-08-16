public class Solution {    
    public String minWindow(String s, String t) {
        int lengthS = s.length();
        int lengthT = t.length();
        
        if (lengthS < lengthT) {
            return "";
        }

        Map<Character, Integer> tCharCounter = new HashMap<>();
        Map<Character, Integer> sCharCounter = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCharCounter.put(c, tCharCounter.getOrDefault(c, 0) + 1);
        }
        
        int needed = tCharCounter.size();
        int acquired = 0;
        int left = 0;
        int[] minWindow = {0, s.length() - 1};
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            sCharCounter.put(rightChar, sCharCounter.getOrDefault(rightChar, 0) + 1);
            if (tCharCounter.containsKey(rightChar) && Objects.equals(sCharCounter.get(rightChar), tCharCounter.get(rightChar))) {
                acquired++;
            }
            
            while (acquired == needed) {
                char leftChar = s.charAt(left);
                sCharCounter.put(leftChar, sCharCounter.get(leftChar) - 1);
                if (!tCharCounter.containsKey(leftChar) || sCharCounter.getOrDefault(leftChar, 0) >= tCharCounter.get(leftChar)) {
                    left++;
                    continue;
                }
                if (minWindow[1] - minWindow[0] > right - left) {
                    minWindow[1] = right;
                    minWindow[0] = left;
                }
                acquired--;
                left++;
            }
        }
        
        return s.substring(minWindow[0], minWindow[1] + 1);
    }
}
