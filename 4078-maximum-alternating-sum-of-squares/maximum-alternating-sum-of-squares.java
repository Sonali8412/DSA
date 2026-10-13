

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (long) Math.abs(nums[i]);
        }
        
        // Sort the absolute values
        Arrays.sort(arr);
        
        long score = 0;
        int mid = n / 2;
        
        // Subtract the squares of the smaller half
        for (int i = 0; i < mid; i++) {
            score -= arr[i] * arr[i];
        }
        
        // Add the squares of the larger half
        for (int i = mid; i < n; i++) {
            score += arr[i] * arr[i];
        }
        
        return score;
    }
}