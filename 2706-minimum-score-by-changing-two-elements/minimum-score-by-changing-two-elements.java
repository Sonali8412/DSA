import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        
        // Since we can change 2 elements, we can always make the low score 0
        // by setting the changed elements equal to an existing element.
        // To minimize the high score (max - min), we only consider 3 optimal cases:
        
        // 1. Change the two smallest elements
        int option1 = nums[n - 1] - nums[2];
        
        // 2. Change the two largest elements
        int option2 = nums[n - 3] - nums[0];
        
        // 3. Change the smallest and the largest element
        int option3 = nums[n - 2] - nums[1];
        
        return Math.min(option1, Math.min(option2, option3));
    }
}