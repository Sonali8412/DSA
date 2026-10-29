import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        
        // The first element must be 1
        arr[0] = 1;
        
        // Greedily enforce the condition arr[i] - arr[i-1] <= 1
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1] + 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        
        // The last element will be the maximum possible value
        return arr[arr.length - 1];
    }
}