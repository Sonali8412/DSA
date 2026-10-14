class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // Check if the sum is within the possible range [0, 9 * num]
        if (sum < 0 || sum > 9 * num) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            // Take the maximum possible digit for the current position, which is at most 9
            // and must leave enough sum for the remaining (num - 1 - i) positions (at most 9 each).
            int digit = Math.min(9, sum);
            
            // Ensure we don't take more than what's left, but also leave enough for the rest.
            // Actually, a simple greedy approach: take max(0, sum - 9 * (num - 1 - i)) 
            // combined with min(9, sum) works.
            // Let's refine the greedy choice for each digit from left to right:
            // We want the largest digit possible, so try 9 down to 0, ensuring remaining sum <= 9 * (num - 1 - i)
            
            int chosen = 0;
            for (int d = 9; d >= 0; d--) {
                if (sum - d <= 9 * (num - 1 - i) && sum - d >= 0) {
                    chosen = d;
                    break;
                }
            }
            
            sb.append(chosen);
            sum -= chosen;
        }

        return sb.toString();
    }
}