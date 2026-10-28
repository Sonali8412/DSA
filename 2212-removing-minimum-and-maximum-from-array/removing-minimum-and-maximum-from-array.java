class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        
        int minIdx = 0, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        
        // Ensure i is the smaller index and j is the larger index
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        
        // Scenario 1: Both from the front
        int option1 = j + 1;
        
        // Scenario 2: Both from the back
        int option2 = n - i;
        
        // Scenario 3: One from the front, one from the back
        int option3 = (i + 1) + (n - j);
        
        return Math.min(option1, Math.min(option2, option3));
    }
}