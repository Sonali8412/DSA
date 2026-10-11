import java.util.*;

class Solution {
    public String majorityFrequencyGroup(String s) {
        // Step 1: Count frequency of each character
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // Step 2: Group characters by their frequency
        Map<Integer, List<Character>> freqMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                int freq = counts[i];
                char c = (char) ('a' + i);
                freqMap.computeIfAbsent(freq, k -> new ArrayList<>()).add(c);
            }
        }
        
        // Step 3: Find the majority frequency group
        int bestFreq = -1;
        int maxDistinctSize = -1;
        
        for (Map.Entry<Integer, List<Character>> entry : freqMap.entrySet()) {
            int freq = entry.getKey();
            int distinctSize = entry.getValue().size();
            
            if (distinctSize > maxDistinctSize) {
                maxDistinctSize = distinctSize;
                bestFreq = freq;
            } else if (distinctSize == maxDistinctSize) {
                if (freq > bestFreq) {
                    bestFreq = freq;
                }
            }
        }
        
        // Step 4: Construct the result string from the chosen group
        List<Character> bestGroup = freqMap.get(bestFreq);
        StringBuilder sb = new StringBuilder();
        for (char c : bestGroup) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}