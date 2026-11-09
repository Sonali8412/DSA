
import java.util.*;

class Solution {

    int A, B, C, D;
    int[] dp;

    // Prime factor contributions of digits 0-9
    int[] f2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    int[] f3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};
    int[] f5 = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    int[] f7 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};

    // Convert four-dimensional state into one-dimensional index
    int index(int a, int b, int c, int d) {
        return (((a * (B + 1) + b) * (C + 1) + c)
                * (D + 1) + d);
    }

    // Factorize t into powers of 2, 3, 5 and 7
    boolean factorize(long t) {

        A = B = C = D = 0;

        while (t % 2 == 0) {
            A++;
            t /= 2;
        }

        while (t % 3 == 0) {
            B++;
            t /= 3;
        }

        while (t % 5 == 0) {
            C++;
            t /= 5;
        }

        while (t % 7 == 0) {
            D++;
            t /= 7;
        }

        return t == 1;
    }

    // DP: minimum digits required to satisfy remaining factors
    void buildDP() {

        int size = (A + 1) * (B + 1) *
                   (C + 1) * (D + 1);

        dp = new int[size];

        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        dp[index(0, 0, 0, 0)] = 0;

        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    for (int d = 0; d <= D; d++) {

                        if (a == 0 && b == 0 &&
                            c == 0 && d == 0) {
                            continue;
                        }

                        int best = Integer.MAX_VALUE / 2;

                        for (int digit = 2; digit <= 9; digit++) {

                            int na = Math.max(0, a - f2[digit]);
                            int nb = Math.max(0, b - f3[digit]);
                            int nc = Math.max(0, c - f5[digit]);
                            int nd = Math.max(0, d - f7[digit]);

                            // Ignore transitions without progress
                            if (na == a && nb == b &&
                                nc == c && nd == d) {
                                continue;
                            }

                            best = Math.min(
                                best,
                                1 + dp[index(na, nb, nc, nd)]
                            );
                        }

                        dp[index(a, b, c, d)] = best;
                    }
                }
            }
        }
    }

    // Construct the lexicographically smallest valid string
    String construct(int length, int a, int b, int c, int d) {

        StringBuilder ans = new StringBuilder();

        for (int pos = 0; pos < length; pos++) {

            int remaining = length - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int na = Math.max(0, a - f2[digit]);
                int nb = Math.max(0, b - f3[digit]);
                int nc = Math.max(0, c - f5[digit]);
                int nd = Math.max(0, d - f7[digit]);

                if (dp[index(na, nb, nc, nd)] <= remaining) {

                    ans.append((char) ('0' + digit));

                    a = na;
                    b = nb;
                    c = nc;
                    d = nd;

                    break;
                }
            }
        }

        return ans.toString();
    }

    public String smallestNumber(String num, long t) {

        // Step 1: Prime factorization
        if (!factorize(t)) {
            return "-1";
        }

        // Step 2: Build DP
        buildDP();

        int n = num.length();

        int[] p2 = new int[n + 1];
        int[] p3 = new int[n + 1];
        int[] p5 = new int[n + 1];
        int[] p7 = new int[n + 1];
        int[] zeros = new int[n + 1];

        // Step 3: Calculate prefix factor counts
        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            p2[i + 1] = p2[i];
            p3[i + 1] = p3[i];
            p5[i + 1] = p5[i];
            p7[i + 1] = p7[i];
            zeros[i + 1] = zeros[i];

            if (digit == 0) {
                zeros[i + 1]++;
                continue;
            }

            p2[i + 1] += f2[digit];
            p3[i + 1] += f3[digit];
            p5[i + 1] += f5[digit];
            p7[i + 1] += f7[digit];
        }

        // Step 4: Check whether original number is valid
        if (zeros[n] == 0 &&
            p2[n] >= A &&
            p3[n] >= B &&
            p5[n] >= C &&
            p7[n] >= D) {

            return num;
        }

        // Step 5: Try changing digits from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Prefix cannot contain zero
            if (zeros[i] > 0) {
                continue;
            }

            int a = Math.max(0, A - p2[i]);
            int b = Math.max(0, B - p3[i]);
            int c = Math.max(0, C - p5[i]);
            int d = Math.max(0, D - p7[i]);

            int original = num.charAt(i) - '0';

            // Find the smallest larger digit
            for (int digit = Math.max(1, original + 1);
                 digit <= 9; digit++) {

                int na = Math.max(0, a - f2[digit]);
                int nb = Math.max(0, b - f3[digit]);
                int nc = Math.max(0, c - f5[digit]);
                int nd = Math.max(0, d - f7[digit]);

                int suffixLength = n - i - 1;

                if (dp[index(na, nb, nc, nd)] <= suffixLength) {

                    StringBuilder ans = new StringBuilder();

                    ans.append(num.substring(0, i));
                    ans.append(digit);

                    ans.append(
                        construct(suffixLength, na, nb, nc, nd)
                    );

                    return ans.toString();
                }
            }
        }

        // Step 6: No answer with the same length
        int length = Math.max(
            n + 1,
            dp[index(A, B, C, D)]
        );

        return construct(length, A, B, C, D);
    }
}
