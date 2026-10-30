class Solution {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3]; // To store frequencies of 'a', 'b', and 'c'
        int left = 0, res = 0;
        
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'a']++;
            
            // While the current window contains at least one 'a', 'b', and 'c'
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // All substrings starting from 'left' up to the end of the string (and beyond 'right') are valid
                res += s.length() - right;
                
                // Shrink the window from the left
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }
        
        return res;
    }
}