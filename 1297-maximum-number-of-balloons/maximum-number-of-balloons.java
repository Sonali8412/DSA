class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];
        
        // Count frequencies of each character in text
        for (int i = 0; i < text.length(); i++) {
            counts[text.charAt(i) - 'a']++;
        }
        
        // Frequencies required for the word "balloon"
        int b = counts['b' - 'a'];
        int a = counts['a' - 'a'];
        int l = counts['l' - 'a'] / 2;
        int o = counts['o' - 'a'] / 2;
        int n = counts['n' - 'a'];
        
        // Find the limiting character
        int minBalloons = Math.min(b, Math.min(a, Math.min(l, Math.min(o, n))));
        
        return minBalloons;
    }
}