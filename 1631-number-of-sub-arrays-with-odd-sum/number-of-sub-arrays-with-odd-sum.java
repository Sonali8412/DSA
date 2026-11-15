class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case for prefix sum 0 (which is even)
        long currentSum = 0;
        long result = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;
            
            if (currentSum % 2 == 1) {
                // If current prefix sum is odd, add the number of previous even prefix sums
                result = (result + evenCount) % MOD;
                oddCount++;
            } else {
                // If current prefix sum is even, add the number of previous odd prefix sums
                result = (result + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) result;
    }
}