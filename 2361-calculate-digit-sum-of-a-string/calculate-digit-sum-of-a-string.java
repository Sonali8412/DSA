class Solution {
    public String digitSum(String s, int k) {
        // Repeat the rounds as long as the length of s is greater than k
        while (s.length() > k) {
            StringBuilder nextRoundStr = new StringBuilder();
            
            // Step 1 & 2: Divide into consecutive groups of size k and calculate digit sums
            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                String group = s.substring(i, end);
                
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += group.charAt(j) - '0';
                }
                
                // Append the sum of the current group to the new string
                nextRoundStr.append(sum);
            }
            
            // Step 3: Update s for the next round
            s = nextRoundStr.toString();
        }
        
        return s;
    }
}