import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Create a sorted copy of the unique elements or all elements
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        // Assign ranks to each unique number
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank++);
            }
        }
        
        // Construct the result array using the ranks
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}