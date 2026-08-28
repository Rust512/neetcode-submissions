class MedianFinder {
    private final PriorityQueue<Integer> leftHalf = new PriorityQueue<>(Collections.reverseOrder());
    private final PriorityQueue<Integer> rightHalf = new PriorityQueue<>();

    public MedianFinder() {
    }
    
    public void addNum(int num) {
        if (rightHalf.isEmpty()) {
            rightHalf.offer(num);
            return;
        }

        if (num >= rightHalf.peek()) {
            rightHalf.offer(num);
            pruneRight();
            return;
        }
        
        leftHalf.offer(num);
        pruneLeft();
        return;
    }

    private void pruneLeft() {
        if (leftHalf.isEmpty()) {
            return;
        }

        if (leftHalf.size() - rightHalf.size() < 2) {
            return;
        }

        int removed = leftHalf.poll();
        rightHalf.offer(removed);
    }

    private void pruneRight() {
        if (rightHalf.isEmpty()) {
            return;
        }

        if (rightHalf.size() - leftHalf.size() < 2) {
            return;
        }

        int removed = rightHalf.poll();
        leftHalf.offer(removed);
    }
    
    public double findMedian() {
        int result = Integer.compare(leftHalf.size(), rightHalf.size());
        return switch(result) {
            case 1 -> 1.0 * leftHalf.peek();
            case -1 -> 1.0 * rightHalf.peek();
            default -> (1.0 * (leftHalf.peek() + rightHalf.peek())) / 2.0;
        };
    }
}
