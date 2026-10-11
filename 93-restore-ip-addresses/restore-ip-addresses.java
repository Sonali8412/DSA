import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, "", result);
        return result;
    }
    
    private void backtrack(String s, int startIndex, int segmentCount, String currentIp, List<String> result) {
        // If we have 4 segments and have used all characters, it's a valid IP
        if (segmentCount == 4) {
            if (startIndex == s.length()) {
                result.add(currentIp);
            }
            return;
        }
        
        // Pruning: if remaining characters are too many or too few for remaining segments
        int remainingSegments = 4 - segmentCount;
        int remainingLength = s.length() - startIndex;
        if (remainingLength < remainingSegments || remainingLength > remainingSegments * 3) {
            return;
        }
        
        // Try taking 1, 2, or 3 digits for the current segment
        for (int i = 1; i <= 3 && startIndex + i <= s.length(); i++) {
            String segment = s.substring(startIndex, startIndex + i);
            
            // Check if the segment is valid:
            // 1. No leading zeros for numbers with more than 1 digit (e.g., "01" is invalid, but "0" is valid)
            // 2. Value must be <= 255
            if ((segment.startsWith("0") && segment.length() > 1) || Integer.parseInt(segment) > 255) {
                continue;
            }
            
            String nextIp = segmentCount == 0 ? segment : currentIp + "." + segment;
            backtrack(s, startIndex + i, segmentCount + 1, nextIp, result);
        }
    }
}