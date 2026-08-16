class MinStack {
    private long min = Long.MAX_VALUE;
    private final Stack<Long> container;

    public MinStack() {
        this.container = new Stack<>();
    }

    public void push(int val) {
        if (container.empty()) {
            container.push(0L);
            min = val;
            return;
        }

        long element = val - min;
        container.push(element);

        if (element < 0) {
            min = val;
        }
    }

    public void pop() {
        if (container.empty()) {
            throw new IllegalArgumentException("The stack is empty");
        }
        long top = container.pop();
        if (top <= 0L) {
            min -= top;
        }
    }

    public int top() {
        if (container.empty()) {
            throw new IllegalArgumentException("The stack is empty");
        }
        long top = container.peek();
        return (int) (min + (top > 0 ? top : 0));
    }

    public int getMin() {
        return (int) min;
    }
}
