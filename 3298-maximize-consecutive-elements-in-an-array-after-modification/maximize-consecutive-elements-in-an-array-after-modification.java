import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int nextChain = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), nextChain));

            int currChain = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, Math.max(dp.getOrDefault(x, 0), currChain));

            ans = Math.max(ans, Math.max(nextChain, currChain));
        }

        return ans;
    }
}