class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            count[c - 'a']++;
            
            // If any character count exceeds 2, shrink the window from the left
            while (count[c - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}