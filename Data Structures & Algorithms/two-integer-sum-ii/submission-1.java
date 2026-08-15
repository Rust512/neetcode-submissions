class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length < 2) {
            return new int[0];
        }

        for (int leftPointer = 0; leftPointer < numbers.length - 1; leftPointer++) {
            int rightPointer = searchRange(numbers, leftPointer + 1, numbers.length - 1, target - numbers[leftPointer]);
            if (rightPointer == -1) {
                continue;
            }
            return new int[]{leftPointer + 1, rightPointer + 1};
        }

        return new int[0];
    }

    private int searchRange(int[] numbers, int start, int end, int key) {
        if (start < 0 || end >= numbers.length) {
            return -1;
        }
        int keyIndex;
        while (start <= end) {
            keyIndex = start + (end - start) / 2;
            if (numbers[keyIndex] == key) {
                return keyIndex;
            } else if (numbers[keyIndex] < key) {
                start = keyIndex + 1;
            } else {
                end = keyIndex - 1;
            }
        }
        return -1;
    }
}
