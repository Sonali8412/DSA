import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int totalNumbers = 1 << n; // 2^n
        
        for (int i = 0; i < totalNumbers; i++) {
            // Formula to generate the i-th Gray code: i ^ (i >> 1)
            result.add(i ^ (i >> 1));
        }
        
        return result;
    }
}