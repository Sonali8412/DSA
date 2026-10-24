class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int totalWeight = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                totalWeight += weights[c - 'a'];
            }
            int modValue = totalWeight % 26;
            char mappedChar = (char) ('z' - modValue);
            sb.append(mappedChar);
        }

        return sb.toString();
    }
}