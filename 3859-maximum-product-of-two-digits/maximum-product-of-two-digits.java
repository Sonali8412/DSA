import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int maxProduct(int n) {
        List<Integer> digits = new ArrayList<>();
        
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        
        Collections.sort(digits);
        int size = digits.size();
        
        // Return the product of the two largest digits
        return digits.get(size - 1) * digits.get(size - 2);
    }
}