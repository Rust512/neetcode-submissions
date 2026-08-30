class Solution {
    private static record CarPosition(int startPos, int speed) {
        private double timeToReachEnd(int endPos) {
            assert speed > 0;
            return 1.0 * (endPos - startPos) / speed;
        }
    }
    
    public int carFleet(int target, int[] position, int[] speed) {
        List<CarPosition> positions = new ArrayList<>();
        int n = position.length;

        for (int i = 0; i < n; i++) {
            positions.add(new CarPosition(position[i], speed[i]));
        }

        positions.sort(Comparator.comparing(CarPosition::startPos).reversed());

        Stack<Double> container = new Stack<>();
        for (CarPosition entry : positions) {
            double timeToReachEnd = entry.timeToReachEnd(target);
            if (container.isEmpty() || timeToReachEnd > container.peek()) {
                container.push(timeToReachEnd);
            }
        }

        return container.size();
    }
}
