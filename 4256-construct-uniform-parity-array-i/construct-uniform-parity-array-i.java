class Solution {
    public boolean uniformArray(int[] nums1) {
        int oddCount = 0;
        for (int num : nums1) {
            if (num % 2 != 0) {
                oddCount++;
            }
        }
        
        // If there are no odd numbers, we can only achieve all-even 
        // (which is already the case since all are even).
        // If there is at least one odd number, we can adjust parities.
        return true; 
    }
}