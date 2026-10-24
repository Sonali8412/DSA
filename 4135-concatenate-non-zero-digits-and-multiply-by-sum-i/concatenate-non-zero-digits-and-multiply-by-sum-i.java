class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;
        
        StringBuilder sb = new StringBuilder();
        String s = String.valueOf(n);
        
        // Extract all non-zero digits
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '0') {
                sb.append(c);
            }
        }
        
        if (sb.length() == 0) return 0;
        
        long x = Long.parseLong(sb.toString());
        long sum = 0;
        
        // Calculate the sum of digits in x
        String xStr = sb.toString();
        for (int i = 0; i < xStr.length(); i++) {
            sum += xStr.charAt(i) - '0';
        }
        
        return x * sum;
    }
}