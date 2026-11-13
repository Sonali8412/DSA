class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        StringBuilder firstHalf = new StringBuilder();
        char midChar = 0;
        
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            int freq = count[i];
            
            // Append half of the occurrences to the first half
            for (int j = 0; j < freq / 2; j++) {
                firstHalf.append(c);
            }
            
            // If frequency is odd, this character goes in the center
            if (freq % 2 != 0) {
                midChar = c;
            }
        }
        
        StringBuilder result = new StringBuilder(firstHalf);
        if (midChar != 0) {
            result.append(midChar);
        }
        result.append(firstHalf.reverse());
        
        return result.toString();
    }
}