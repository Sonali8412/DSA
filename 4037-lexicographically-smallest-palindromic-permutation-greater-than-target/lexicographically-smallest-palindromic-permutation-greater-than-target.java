import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int oddCount = 0;
        int midChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                midChar = i;
            }
        }

        // A palindromic permutation requires at most 1 odd frequency character (for odd n)
        // or 0 odd frequency characters (for even n).
        if (oddCount > (n % 2 == 1 ? 1 : 0)) {
            return "";
        }

        int half = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        char[] ans = new char[n];
        if (midChar != -1) {
            ans[half] = (char) ('a' + midChar);
        }

        // Use backtracking to find the smallest valid first half
        String result = dfs(0, half, halfFreq, ans, target, new boolean[half + 1]);
        return result != null ? result : "";
    }

    private String dfs(int idx, int half, int[] freq, char[] ans, String target, boolean[] isGreater) {
        if (idx == half) {
            // Mirror the first half to form the second half
            for (int i = 0; i < half; i++) {
                ans[ans.length - 1 - i] = ans[i];
            }
            String candidate = new String(ans);
            return candidate.compareTo(target) > 0 ? candidate : null;
        }

        char targetChar = target.charAt(idx);

        // Try characters from 'a' to 'z' to ensure lexicographically smallest result
        for (int c = 0; c < 26; c++) {
            if (freq[c] > 0) {
                char ch = (char) ('a' + c);

                // If we haven't yet surpassed the target prefix, we can't pick a character smaller than target's char at this position
                if (!isGreater[idx] && ch < targetChar) {
                    continue;
                }

                freq[c]--;
                ans[idx] = ch;

                isGreater[idx + 1] = isGreater[idx] || (ch > targetChar);

                String res = dfs(idx + 1, half, freq, ans, target, isGreater);
                if (res != null) {
                    return res;
                }

                freq[c]++; // backtrack
            }
        }

        return null;
    }
}