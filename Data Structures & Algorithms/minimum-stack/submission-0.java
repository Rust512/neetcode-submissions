class MinStack {

        private final Stack<Integer> container;
        private final TreeMap<Integer, Integer> minContainer;

        public MinStack() {
            this.container = new Stack<>();
            this.minContainer = new TreeMap<>();
        }

        public void push(int val) {
            container.push(val);
            minContainer.merge(val, 1, (oldValue, newValue) -> oldValue + 1);
        }

        public void pop() {
            if (container.empty()) {
                throw new IllegalArgumentException("The stack is empty!");
            }
            int roof = top();
            minContainer.put(roof, minContainer.get(roof) - 1);
            if (minContainer.get(roof) == 0) {
                minContainer.remove(roof);
            }
            container.pop();
        }

        public int top() {
            if (container.empty()) {
                throw new IllegalArgumentException("The stack is empty!");
            }
            return container.peek();
        }

        public int getMin() {
            if (container.empty()) {
                throw new IllegalArgumentException("The stack is empty!");
            }
            return minContainer.firstKey();
        }
    }
