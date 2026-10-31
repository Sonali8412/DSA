class Solution {
    public String countAndSay(int n) {
        String current = "1";
        
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int length = current.length();
            int count = 1;
            
            for (int j = 0; j < length; j++) {
                // If the next character is the same, increment the count
                if (j + 1 < length && current.charAt(j) == current.charAt(j + 1)) {
                    count++;
                } else {
                    // Append the frequency and the character itself
                    next.append(count).append(current.charAt(j));
                    count = 1; // Reset count for the next group
                }
            }
            
            current = next.toString();
        }
        
        return current;
    }
}