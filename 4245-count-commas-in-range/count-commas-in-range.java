class Solution {
    public int countCommas(int n) {
        // Numbers from 1 to 999 have 0 commas.
        // Numbers from 1000 to n have 1 comma each (since n <= 10^5).
        if (n < 1000) {
            return 0;
        }
        
        // Total numbers from 1000 to n inclusive
        return n - 1000 + 1;
    }
}