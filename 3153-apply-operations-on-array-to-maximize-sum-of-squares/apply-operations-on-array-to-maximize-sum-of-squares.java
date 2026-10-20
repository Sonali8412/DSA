import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCounts = new int[30];
        
        // Step 1: Count the frequency of each bit across all numbers
        for (int num : nums) {
            for (int bit = 0; bit < 30; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCounts[bit]++;
                }
            }
        }
        
        long mod = 1_000_000_007;
        long totalSumOfSquares = 0;
        
        // Step 2: Greedily build the k largest numbers
        for (int i = 0; i < k; i++) {
            long currentNum = 0;
            for (int bit = 0; bit < 30; bit++) {
                if (bitCounts[bit] > 0) {
                    currentNum |= (1 << bit);
                    bitCounts[bit]--;
                }
            }
            totalSumOfSquares = (totalSumOfSquares + (currentNum * currentNum) % mod) % mod;
        }
        
        return (int) totalSumOfSquares;
    }
}