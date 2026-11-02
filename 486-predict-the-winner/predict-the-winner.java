class Solution {
    private Integer[][] memo;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        memo = new Integer[n][n];
        return scoreDiff(nums, 0, n - 1) >= 0;
    }

    private int scoreDiff(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        // Choose left or right and subtract the opponent's optimal response
        int pickLeft = nums[left] - scoreDiff(nums, left + 1, right);
        int pickRight = nums[right] - scoreDiff(nums, left, right - 1);

        return memo[left][right] = Math.max(pickLeft, pickRight);
    }
}