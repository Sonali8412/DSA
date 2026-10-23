class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int mx = Integer.MIN_VALUE;
        int mn = Integer.MAX_VALUE;
        
        for (int x : nums) {
            mx = Math.max(mx, x);
            mn = Math.min(mn, x);
        }
        
        return 1L * k * (mx - mn);
    }
}