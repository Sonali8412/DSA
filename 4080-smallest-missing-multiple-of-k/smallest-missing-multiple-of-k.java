import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        int multiple = k;
        while (true) {
            if (!set.contains(multiple)) {
                return multiple;
            }
            multiple += k;
        }
    }
}