class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;
        
        // Calculate initial sums and count '?' for both halves
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }
        
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }
        
        // Total sum difference and total '?' difference
        int sumDiff = leftSum - rightSum;
        int qDiff = leftQ - rightQ;
        
        // If the total number of '?' is odd, Alice always wins because Bob can never 
        // perfectly balance the number of turns/replacements.
        if ((leftQ + rightQ) % 2 != 0) {
            return true;
        }
        
        // If the counts of '?' are equal on both sides, Bob wins if and only if 
        // the existing digit sums are already equal.
        if (qDiff == 0) {
            return sumDiff != 0;
        }
        
        // If '?' are uneven, Alice wins if the required sum offset matches 
        // 9 * (difference in question marks / 2)
        return sumDiff != -9 * (qDiff / 2);
    }
}