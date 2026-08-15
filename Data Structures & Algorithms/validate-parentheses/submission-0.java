class Solution {
    public boolean isValid(String s) {
        Stack<Character> container = new Stack<>();
        for (char brace : s.toCharArray()) {
            if (brace == '(' || brace == '[' || brace == '{') {
                container.push(brace);
                continue;
            }

            if (container.isEmpty()) {
                return false;
            }

            boolean valid = switch (brace) {
                case ')' -> container.peek() == '(';
                case ']' -> container.peek() == '[';
                case '}' -> container.peek() == '{';
                default -> false;
            };

            if (!valid) {
                return false;
            }

            container.pop();
        }

        if (!container.isEmpty()) {
            return false;
        }

            return true;
    }
}
