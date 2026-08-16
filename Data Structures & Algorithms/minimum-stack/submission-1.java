class MinStack {
    private int min = Integer.MAX_VALUE;
    private final Stack<Integer> container;

    public MinStack() {
        this.container = new Stack<>();
    }

    public void push(int val) {
        if (container.empty()) {
            container.push(0);
            min = val;
            return;
        }

        int element = val - min;
        container.push(element);

        if (element < 0) {
            min = val;
        }
    }

    public void pop() {
        if (container.empty()) {
            throw new IllegalArgumentException("The stack is empty");
        }
        int top = container.pop();
        if (top <= 0) {
            min -= top;
        }
    }

    public int top() {
        if (container.empty()) {
            throw new IllegalArgumentException("The stack is empty");
        }
        int top = container.peek();
        return min + (top > 0 ? top : 0);
    }

    public int getMin() {
        return min;
    }
}
