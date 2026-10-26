class Solution {
    public int maxIceCream(int[] costs, long coins) {
        // Find the maximum cost to size our frequency array
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }
        
        // Count the frequencies of each cost
        int[] count = new int[maxCost + 1];
        for (int cost : costs) {
            count[cost]++;
        }
        
        int barsBought = 0;
        
        // Greedily buy the cheapest ice cream bars first
        for (int c = 1; c <= maxCost; c++) {
            if (count[c] == 0) continue;
            
            long totalCostForCurrent = (long) c * count[c];
            if (coins >= totalCostForCurrent) {
                coins -= totalCostForCurrent;
                barsBought += count[c];
            } else {
                int canBuy = (int) (coins / c);
                barsBought += canBuy;
                break;
            }
        }
        
        return barsBought;
    }
}