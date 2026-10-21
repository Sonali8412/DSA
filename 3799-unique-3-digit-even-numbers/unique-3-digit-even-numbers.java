class Solution {
    public int totalNumbers(int[] digits) {
        // Count the frequency of each digit (0-9)
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        
        int uniqueEvenCount = 0;
        
        // Iterate through all 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;
            
            // Temporary frequency array for the current 3-digit number
            int[] tempCount = new int[10];
            tempCount[hundreds]++;
            tempCount[tens]++;
            tempCount[units]++;
            
            // Check if we have enough of each digit to form this number
            boolean possible = true;
            for (int i = 0; i < 10; i++) {
                if (tempCount[i] > count[i]) {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                uniqueEvenCount++;
            }
        }
        
        return uniqueEvenCount;
    }
}