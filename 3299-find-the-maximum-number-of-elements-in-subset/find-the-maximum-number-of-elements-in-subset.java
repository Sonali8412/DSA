
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 1;

        // Handle x = 1 separately
        int ones = freq.getOrDefault(1L, 0);

        if (ones > 0) {
            maxLen = Math.max(maxLen,
                    ones % 2 == 0 ? ones - 1 : ones);
        }

        // Handle all numbers greater than 1
        for (long start : freq.keySet()) {

            if (start == 1) {
                continue;
            }

            long curr = start;
            int len = 0;

            while (freq.getOrDefault(curr, 0) >= 2) {

                // Prevent overflow and avoid impossible squares
                if (curr > 1_000_000_000L / curr) {
                    break;
                }

                len += 2;
                curr = curr * curr;
            }

            // Add the middle element if it exists
            if (freq.containsKey(curr)) {
                len++;
            } else {
                len--;
            }

            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }
}
