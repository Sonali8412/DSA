public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int position = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int pivotIndex = partition(nums, lo, hi);
            if (pivotIndex < position)
                lo = pivotIndex + 1;
            else if (pivotIndex > position)
                hi = pivotIndex - 1;
            else
                break;
        }
        return nums[position];
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[i] <= pivot && i < right) i++;
            while (arr[j] > pivot && j > left) j--;
            if (i < j) swapNum(arr, i, j);
        }
        swapNum(arr, left, j);
        return j;
    }

    private void swapNum(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
