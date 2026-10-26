class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int count = 0;
        
        // Iterate through all possible starting points of subarrays
        for (int i = 0; i < n; i++) {
            int targetCount = 0;
            int totalLength = 0;
            
            // Expand the subarray from i to j
            for (int j = i; j < n; j++) {
                totalLength++;
                if (nums[j] == target) {
                    targetCount++;
                }
                
                // Check if target appears strictly more than half the time
                if (2 * targetCount > totalLength) {
                    count++;
                }
            }
        }
        
        return count;
    }
}