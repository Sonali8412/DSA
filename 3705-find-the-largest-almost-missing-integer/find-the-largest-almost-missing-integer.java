class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        if (k == 1) {
            int max = -1;
            for (int i = 0; i < n; i++) {
                boolean unique = true;
                for (int j = 0; j < n; j++) {
                    if (i != j && nums[i] == nums[j]) {
                        unique = false;
                        break;
                    }
                }
                if (unique && nums[i] > max) {
                    max = nums[i];
                }
            }
            return max;
        }
        
        if (k == n) {
            int max = -1;
            for (int num : nums) {
                if (num > max) {
                    max = num;
                }
            }
            return max;
        }
        
        // For 1 < k < n, only the first element (nums[0]) and the last element (nums[n - 1]) 
        // can possibly appear in exactly one subarray of size k.
        int ans = -1;
        
        // Check nums[0]
        int countFirst = 0;
        for (int i = 0; i <= n - k; i++) {
            boolean found = false;
            for (int j = i; j < i + k; j++) {
                if (nums[j] == nums[0]) {
                    found = true;
                    break;
                }
            }
            if (found) countFirst++;
        }
        if (countFirst == 1) {
            ans = Math.max(ans, nums[0]);
        }
        
        // Check nums[n - 1]
        int countLast = 0;
        for (int i = 0; i <= n - k; i++) {
            boolean found = false;
            for (int j = i; j < i + k; j++) {
                if (nums[j] == nums[n - 1]) {
                    found = true;
                    break;
                }
            }
            if (found) countLast++;
        }
        if (countLast == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}