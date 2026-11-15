import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Store key-value pairs in a HashMap for O(1) lookup
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                // Find the closing bracket
                int closeIdx = s.indexOf(')', i);
                String key = s.substring(i + 1, closeIdx);
                
                // Append the mapped value or "?" if not found
                result.append(map.getOrDefault(key, "?"));
                
                // Move pointer to the closing bracket (loop increment will move it past)
                i = closeIdx;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}