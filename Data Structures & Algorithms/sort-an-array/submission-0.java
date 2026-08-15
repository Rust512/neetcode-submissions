class Solution {

    private static final Random generator = new Random();


    private void sortArray(int[] arr, int start, int end) {
        validateIndex(arr.length, start);
        validateIndex(arr.length, end);
        int pivotIndex = partition(arr, start, end);
        if (pivotIndex > start) {
            sortArray(arr, start, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            sortArray(arr, pivotIndex + 1, end);
        }
    }

    private int getRandomIndex(int length) {
        return generator.nextInt(0, length);
    }

    /**
     * Swap elements at the indices i and j in the given integer array
     *
     * @param arr an integer array
     * @param i   an index in the integer array
     * @param j   another index in the integer array
     */
    private void swapAtIndices(int[] arr, int i, int j) {
        int size = arr.length;
        validateIndex(size, i);
        validateIndex(size, j);
        if (i == j) {
            return;
        }
        arr[j] = arr[i] + arr[j];
        arr[i] = arr[j] - arr[i];
        arr[j] = arr[j] - arr[i];
    }

    private int partition(int[] arr, int start, int end) {
        int size = end - start + 1;
        validateIndex(arr.length, start);
        validateIndex(arr.length, end);
        if (size == 1) {
            return start;
        }

        int pivotIndex = start + getRandomIndex(size);
        int pivot = arr[pivotIndex];

        int slow = start - 1;
        swapAtIndices(arr, pivotIndex, end);
        for (int fast = start; fast <= end; fast++) {
            if (arr[fast] > pivot) continue;
            swapAtIndices(arr, ++slow, fast);
        }

        return slow;
    }

    private void validateIndex(int size, int i) {
        assert i >= 0 && i < size : "Please provide a valid index";
    }

    public int[] sortArray(int[] nums) {
        int size = nums.length;
        if (size <= 1) {
            return nums;
        }

        sortArray(nums, 0, size - 1);

        return nums;
    }
}