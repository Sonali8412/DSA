
class Solution {
    private char[] s;
    private Long[][][][][] memoCount;
    private Long[][][][][] memoWaviness;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long upper) {
        if (upper < 0) return 0;

        s = String.valueOf(upper).toCharArray();
        int n = s.length;

        memoCount = new Long[n + 1][2][2][11][11];
        memoWaviness = new Long[n + 1][2][2][11][11];

        return dp(0, true, false, 10, 10)[1];
    }

    // Returns {count of numbers, total waviness}
    private long[] dp(int idx, boolean tight, boolean started,
                      int prev1, int prev2) {

        if (idx == s.length) {
            return new long[]{1, 0};
        }

        int t = tight ? 1 : 0;
        int st = started ? 1 : 0;

        if (memoCount[idx][t][st][prev1][prev2] != null) {
            return new long[]{
                memoCount[idx][t][st][prev1][prev2],
                memoWaviness[idx][t][st][prev1][prev2]
            };
        }

        int limit = tight ? s[idx] - '0' : 9;
        long totalCount = 0;
        long totalWaviness = 0;

        for (int digit = 0; digit <= limit; digit++) {
            boolean nextTight =
                tight && (digit == s[idx] - '0');

            long[] child;

            if (!started && digit == 0) {
                // Skip leading zeros.
                child = dp(idx + 1, nextTight, false, 10, 10);
            } else if (!started) {
                // First actual digit.
                child = dp(idx + 1, nextTight, true, digit, 10);
            } else {
                long currentWaviness = 0;

                if (prev1 != 10 && prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > digit) ||
                        (prev1 < prev2 && prev1 < digit)) {
                        currentWaviness = 1;
                    }
                }

                child = dp(idx + 1, nextTight, true, digit, prev1);

                // Count the contribution for EVERY completion.
                totalWaviness += currentWaviness * child[0];
            }

            totalCount += child[0];
            totalWaviness += child[1];
        }

        memoCount[idx][t][st][prev1][prev2] = totalCount;
        memoWaviness[idx][t][st][prev1][prev2] = totalWaviness;

        return new long[]{totalCount, totalWaviness};
    }
}
