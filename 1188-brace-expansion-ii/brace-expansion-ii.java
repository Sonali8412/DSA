import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);
        return sortedList;
    }

    private Set<String> parse(String expr) {
        Set<String> currentGroup = new TreeSet<>();
        List<Set<String>> groups = new ArrayList<>();
        
        int level = 0;
        int start = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '{') {
                if (level == 0) start = i + 1;
                level++;
            } else if (c == '}') {
                level--;
                if (level == 0) {
                    Set<String> subResult = parse(expr.substring(start, i));
                    currentGroup = multiply(currentGroup, subResult);
                }
            } else if (c == ',' && level == 0) {
                groups.add(currentGroup);
                currentGroup = new TreeSet<>();
            } else if (level == 0) {
                // Single letter
                Set<String> single = new TreeSet<>();
                single.add(String.valueOf(c));
                currentGroup = multiply(currentGroup, single);
            }
        }
        
        groups.add(currentGroup);
        
        Set<String> finalSet = new TreeSet<>();
        for (Set<String> group : groups) {
            finalSet.addAll(group);
        }
        return finalSet;
    }

    private Set<String> multiply(Set<String> left, Set<String> right) {
        if (left.isEmpty()) return right;
        if (right.isEmpty()) return left;

        Set<String> result = new TreeSet<>();
        for (String l : left) {
            for (String r : right) {
                result.add(l + r);
            }
        }
        return result;
    }
}