import java.util.Arrays;

class Solution {
    public int minimumCost(int[] cost) {
        // Sort the array in ascending order first
        Arrays.sort(cost);
        
        int totalCost = 0;
        int n = cost.length;
        
        // Traverse from right to left (descending order)
        for (int i = n - 1, count = 1; i >= 0; i--, count++) {
            // Every 3rd candy is free, so we don't add its cost
            if (count % 3 != 0) {
                totalCost += cost[i];
            }
        }
        
        return totalCost;
    }
}