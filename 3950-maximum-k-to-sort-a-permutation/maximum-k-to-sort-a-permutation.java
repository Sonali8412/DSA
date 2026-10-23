class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        // Find the bit ceil for n - 1 (or all bits set up to n-1)
        int k0 = Integer.highestOneBit(n) * 2 - 1;
        int k = k0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k &= nums[i];
            }
        }
        
        return (k == k0) ? 0 : k;
    }
}