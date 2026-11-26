import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {
            int j = i;
            int lineWordChars = 0;

            // Greedily fit words[i .. j - 1] with at least 1 space between words
            while (j < n && lineWordChars + words[j].length() + (j - i) <= maxWidth) {
                lineWordChars += words[j].length();
                j++;
            }

            int numWords = j - i;
            int totalSpaces = maxWidth - lineWordChars;
            StringBuilder line = new StringBuilder();

            // Left-justify if it's the last line or contains only one word
            if (j == n || numWords == 1) {
                for (int k = i; k < j; k++) {
                    if (k > i) {
                        line.append(' ');
                    }
                    line.append(words[k]);
                }
                while (line.length() < maxWidth) {
                    line.append(' ');
                }
            } else {
                // Distribute spaces evenly; extra spaces go to leftmost gaps
                int gaps = numWords - 1;
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        int spacesToAdd = baseSpaces + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToAdd; s++) {
                            line.append(' ');
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}