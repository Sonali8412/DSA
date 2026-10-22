class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Find the minimum odd number
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // If there are no odd numbers, it's already all even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }
        
        // Check if any even number is strictly less than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}