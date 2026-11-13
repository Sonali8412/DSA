class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean allZeros = true;
        
        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZeros = false;
            }
        }
        
        // If all elements are 0, no non-zero XOR subsequence can be formed
        if (allZeros) {
            return 0;
        }
        
        // If total XOR is non-zero, take the whole array
        if (totalXor != 0) {
            return nums.length;
        }
        
        // If total XOR is 0, we can remove one element to make it non-zero
        return nums.length - 1;
    }
}