class Solution { 
    public void reverseString(char[] s) {

        StringBuilder newStr = new StringBuilder(new String(s));
        newStr.reverse();

        for(int i = 0; i < s.length; i++){
            s[i] = newStr.charAt(i);
        }
    }
}