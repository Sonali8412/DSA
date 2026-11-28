import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;
        
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }
        
        // multCount[f] = total items whose factor is a multiple of f
        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
        }
        
        // Pair each item's price with its gain (excluding itself: multCount[f] - 1)
        int[][] itemData = new int[n][2];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            int p = items[i][1];
            itemData[i][0] = p;
            itemData[i][1] = multCount[f] - 1;
        }
        
        // Sort items by price ascending
        Arrays.sort(itemData, (a, b) -> Integer.compare(a[0], b[0]));
        
        long totalItems = 0;
        long remBudget = budget;
        
        for (int[] data : itemData) {
            int price = data[0];
            int gain = data[1];
            
            if (gain <= 0) continue;
            
            // If buying 2 copies of the cheapest item is cheaper or equal to 1 boosted copy,
            // stop buying boosted copies and spend the rest on minPrice.
            if ((long) price >= 2L * minPrice) {
                break;
            }
            
            long canBuy = Math.min((long) gain, remBudget / price);
            totalItems += canBuy * 2;
            remBudget -= canBuy * price;
            
            // If we couldn't buy the full gain batch, we ran out of budget for this price tier
            if (canBuy < gain) {
                break;
            }
        }
        
        // Spend any leftover budget on the single cheapest item (1 item per copy)
        totalItems += remBudget / minPrice;
        
        return (int) totalItems;
    }
}