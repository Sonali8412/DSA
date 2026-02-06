class Solution {
    public int reverse(int x) {

        boolean isNegativeNumber = false;
        if(x < 0) {
            isNegativeNumber = true;
            x = -1 * x;
        }

        int result = 0;
        int previousVal = 0;
        while(x > 0) {

            int mod = x % 10;
            result = result * 10 + mod;
            if((result - mod) / 10 != previousVal) return 0;
            previousVal = result;
            x = x / 10;
        }

        return isNegativeNumber ? -1 * result : result;
    }
}