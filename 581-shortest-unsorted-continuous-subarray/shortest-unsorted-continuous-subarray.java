class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int leftMax = nums[0];
        int rightMin = nums[n - 1];
        int start = -1, end = -2;

        // Traverse from left to right to find the 'end' boundary
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            if (nums[i] < leftMax) {
                end = i;
            }
        }

        // Traverse from right to left to find the 'start' boundary
        for (int i = n - 1; i >= 0; i--) {
            rightMin = Math.min(rightMin, nums[i]);
            if (nums[i] > rightMin) {
                start = i;
            }
        }

        return end - start + 1;
    }
}