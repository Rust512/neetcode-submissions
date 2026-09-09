class Solution {
    private List<String> parenthesis;
    
    public List<String> generateParenthesis(int n) {
        parenthesis = new ArrayList<>();
        StringBuilder builder = new StringBuilder("(");
        helper(builder, 1, 0, n);
        return parenthesis;
    }

    private void helper(StringBuilder builder, int leftCount, int rightCount, int n) {
        if (rightCount > leftCount) {
            return;
        }

        if (leftCount == n && rightCount == n) {
            parenthesis.add(builder.toString());
            return;
        }

        if (leftCount < n) {
            builder.append('(');
            helper(builder, leftCount + 1, rightCount, n);
            builder.setLength(builder.length() - 1);
        }

        builder.append(')');
        helper(builder, leftCount, rightCount + 1, n);
        builder.setLength(builder.length() - 1);
    }
}
