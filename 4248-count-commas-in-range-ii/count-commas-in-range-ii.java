class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long start = 1000; // Numbers with fewer than 4 digits have 0 commas
        long commasPerNumber = 1;
        
        while (start <= n) {
            long end = Math.min(n, start * 1000 - 1);
            long count = end - start + 1;
            totalCommas += count * commasPerNumber;
            
            start *= 1000;
            commasPerNumber++;
        }
        
        return totalCommas;
    }
}