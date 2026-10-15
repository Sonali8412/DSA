import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Base index for the start of valid substrings
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop(); // Remove the matching '(' index or the base -1
                
                if (stack.isEmpty()) {
                    // If stack is empty, current ')' is a mismatch, push its index as the new base
                    stack.push(i);
                } else {
                    // Valid parentheses found, calculate length
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        
        return maxLength;
    }
}