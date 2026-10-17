import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (i != first[c]) continue; // Only check from the first occurrence of each character
            
            int right = last[c];
            boolean isValid = true;
            
            // Expand the window to satisfy condition 2 for all characters inside
            for (int j = i; j <= right; j++) {
                int currChar = s.charAt(j) - 'a';
                if (first[currChar] < i) {
                    isValid = false;
                    break;
                }
                right = Math.max(right, last[currChar]);
            }
            
            if (isValid) {
                intervals.add(new int[]{i, right});
            }
        }
        
        // Greedily select non-overlapping intervals with the earliest end times
        intervals.sort(Comparator.comparingInt(a -> a[1]));
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }
        
        return result;
    }
}