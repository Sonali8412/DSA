import java.util.HashMap;

class Solution {
    public int longestPalindrome(String s) {
        // Count the frequency of each character
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean hasOdd = false;

        for (int count : countMap.values()) {
            if (count % 2 == 0) {
                length += count; // Add all if even
            } else {
                length += count - 1; // Add the largest even part
                hasOdd = true;       // We can use one odd character in the center
            }
        }

        // If there was at least one odd frequency character, we can place one in the middle
        if (hasOdd) {
            length += 1;
        }

        return length;
    }
}