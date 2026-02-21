class Solution {
    public int compress(char[] chars) {

        String newStr = "";

        for (int i = 0; i < chars.length; i++) {

            int count = 1;

            // count repeating characters
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }

            // add character
            newStr += chars[i];

            // add count if > 1
            if (count > 1) {
                newStr += count;
            }
        }

        // copy back to original array
        for (int i = 0; i < newStr.length(); i++) {
            chars[i] = newStr.charAt(i);
        }

        return newStr.length();
    }
}