class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 1;
        long high = (long) coins[0] * k; // Upper bound
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (count(mid, coins) >= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    // Count how many unique amounts can be formed <= mid using the coins
    private long count(long mid, int[] coins) {
        long total = 0;
        int n = coins.length;
        int totalSubsets = 1 << n;
        
        for (int i = 1; i < totalSubsets; i++) {
            long lcmVal = 1;
            int setBits = 0;
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    setBits++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > mid) {
                        break; // Exceeds mid, no need to continue for this subset
                    }
                }
            }
            
            if (lcmVal <= mid) {
                if (setBits % 2 == 1) {
                    total += mid / lcmVal;
                } else {
                    total -= mid / lcmVal;
                }
            }
        }
        
        return total;
    }
    
    private long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    private long lcm(long a, long b) {
        // Prevent potential overflow during multiplication
        return (a / gcd(a, b)) * b;
    }
}