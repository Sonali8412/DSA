import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] indices = new Integer[n];
        int[] digitSums = new int[n];
        
        for (int i = 0; i < n; i++) {
            indices[i] = i;
            digitSums[i] = getDigitSum(nums[i]);
        }
        
        // Sort indices based on digit sum, then by value
        Arrays.sort(indices, (a, b) -> {
            if (digitSums[a] != digitSums[b]) {
                return Integer.compare(digitSums[a], digitSums[b]);
            }
            return Integer.compare(nums[a], nums[b]);
        });
        
        // Find the minimum number of swaps using cycle detection
        boolean[] visited = new boolean[n];
        int swaps = 0;
        
        for (int i = 0; i < n; i++) {
            if (visited[i] || indices[i] == i) {
                continue;
            }
            
            // Traverse the cycle
            int cycleSize = 0;
            int curr = i;
            while (!visited[curr]) {
                visited[curr] = true;
                curr = indices[curr];
                cycleSize++;
            }
            
            if (cycleSize > 1) {
                swaps += (cycleSize - 1);
            }
        }
        
        return swaps;
    }
    
    private int getDigitSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}