import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final long MOD = 1_000_000_007;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Step 1: Precompute binomial coefficients using Pascal's Triangle
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            table[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        // Convert array to a List for easy partitioning
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract 1 and cast the result safely to int
        long totalWays = (getCount(list) - 1 + MOD) % MOD;
        return (int) totalWays;
    }

    private long getCount(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition elements into left and right subtrees
        for (int i = 1; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = getCount(left);
        long rightWays = getCount(right);

        int L = left.size();
        int R = right.size();

        // Combinatorics: choose L positions out of (L + R) for the left subtree elements
        long ways = table[L + R][L];
        
        // Multiply by combinations of left and right subtrees, taking modulo at each step
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}