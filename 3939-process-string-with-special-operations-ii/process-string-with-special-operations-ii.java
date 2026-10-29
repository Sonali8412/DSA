class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];
        len[0] = 0;

        // Step 1: Forward pass to track lengths
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            long currentLen = len[i];
            if (c == '*') {
                len[i + 1] = Math.max(0, currentLen - 1);
            } else if (c == '#') {
                len[i + 1] = currentLen > 0 ? Math.min((long) 2e18, currentLen * 2) : 0;
            } else if (c == '%') {
                len[i + 1] = currentLen;
            } else {
                len[i + 1] = Math.min((long) 2e18, currentLen + 1);
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        // Step 2: Backward pass to find the k-th character
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long prevLen = len[i];

            if (c == '*') {
                // If length before '*' was greater than current length, the last char was removed
                if (prevLen > len[i + 1] && k == len[i + 1]) {
                    // This case handles if k pointed to the removed character, but bounds check prevents it.
                }
            } else if (c == '#') {
                if (k >= prevLen) {
                    k %= prevLen;
                }
            } else if (c == '%') {
                k = prevLen - 1 - k;
            } else {
                if (k == prevLen) {
                    return c;
                }
            }
        }

        return '.';
    }
}