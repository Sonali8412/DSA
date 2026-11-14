
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();

        int totalOnes = 0;
        int maxGain = 0;

        // Previous zero segment length
        int prevZeros = Integer.MIN_VALUE;

        int i = 0;

        while (i < n) {
            int j = i;

            // Find the length of the current segment
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }

            int len = j - i;

            if (s.charAt(i) == '1') {
                totalOnes += len;
            } else {
                // Combine the previous and current zero segments
                maxGain = Math.max(maxGain, prevZeros + len);

                prevZeros = len;
            }

            i = j;
        }

        return totalOnes + maxGain;
    }
}
