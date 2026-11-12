import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }

        Integer[] frequencies = new Integer[26];
        for (int i = 0; i < 26; i++) {
            frequencies[i] = count[i];
        }

        Arrays.sort(frequencies, (a, b) -> b - a);

        int totalPushes = 0;
        for (int i = 0; i < 26; i++) {
            if (frequencies[i] == 0) {
                break;
            }
            totalPushes += frequencies[i] * ((i / 8) + 1);
        }

        return totalPushes;
    }
}