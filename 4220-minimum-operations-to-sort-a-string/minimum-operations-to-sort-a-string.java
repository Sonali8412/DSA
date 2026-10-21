class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        // Check if already sorted
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;
        
        // If length is 2 and not sorted, it's impossible because we can't pick a proper substring
        if (n == 2) return -1;
        
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);
        for (int i = 0; i < n; i++) {
            minChar = (char) Math.min(minChar, s.charAt(i));
            maxChar = (char) Math.max(maxChar, s.charAt(i));
        }
        
        // Check if minChar is at the beginning or maxChar is at the end (proper prefix/suffix)
        boolean minAtStart = (s.charAt(0) == minChar);
        boolean maxAtEnd = (s.charAt(n - 1) == maxChar);
        
        if (minAtStart || maxAtEnd) {
            return 1;
        }
        
        // Check if any minChar exists in any proper prefix and any maxChar in any proper suffix
        boolean minInProperPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                minInProperPrefix = true;
                break;
            }
        }
        
        boolean maxInProperSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == maxChar) {
                maxInProperSuffix = true;
                break;
            }
        }
        
        if (!minInProperPrefix && !maxInProperSuffix) {
            return 3;
        }
        
        return 2;
    }
}