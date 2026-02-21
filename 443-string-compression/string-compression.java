class Solution {
    public int compress(char[] chars) {

        StringBuilder sb = new StringBuilder();

        int read = 0;

        while (read < chars.length) {

            char current = chars[read];
            int count = 0;

            // count frequency
            while (read < chars.length && chars[read] == current) {
                read++;
                count++;
            }

            // append character
            sb.append(current);

            // append count if > 1
            if (count > 1) {
                sb.append(count);
            }
        }

        // copy back to original array
        for (int i = 0; i < sb.length(); i++) {
            chars[i] = sb.charAt(i);
        }

        return sb.length();
    }
}