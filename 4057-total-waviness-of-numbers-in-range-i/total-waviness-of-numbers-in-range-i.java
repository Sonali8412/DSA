class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWavinessSum = 0;
        
        for (int i = num1; i <= num2; i++) {
            totalWavinessSum += getWaviness(i);
        }
        
        return totalWavinessSum;
    }
    
    private int getWaviness(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        
        // Numbers with fewer than 3 digits have a waviness of 0
        if (len < 3) {
            return 0;
        }
        
        int waviness = 0;
        for (int i = 1; i < len - 1; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);
            
            // Peak: strictly greater than both neighbors
            if (curr > prev && curr > next) {
                waviness++;
            }
            // Valley: strictly less than both neighbors
            else if (curr < prev && curr < next) {
                waviness++;
            }
        }
        
        return waviness;
    }
}