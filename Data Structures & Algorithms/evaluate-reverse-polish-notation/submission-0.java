class Solution {
    private boolean isOperator(String symbol) {
        return switch (symbol) {
            case "+", "-", "*", "/" -> true;
            default -> false;
        };
    }

    private int operate(int operand1, int operand2, String operator) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new IllegalArgumentException("Invalid operator!");
        };
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> container = new Stack<>();
        for (String symbol : tokens) {
            if (!isOperator(symbol)) {
                container.push(Integer.parseInt(symbol));
                continue;
            }

            int operand2 = container.pop();
            int operand1 = container.pop();
            container.push(operate(operand1, operand2, symbol));
        }

        return container.pop();
    }
}
