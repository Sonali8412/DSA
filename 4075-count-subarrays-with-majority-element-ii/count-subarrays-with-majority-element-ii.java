import java.util.Arrays;

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (nums[i] == target) ? 1 : -1;
        }
        
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + arr[i];
        }
        
        // Coordinate compression on prefix sum values
        int[] sortedPref = pref.clone();
        Arrays.sort(sortedPref);
        
        FenwickTree ft = new FenwickTree(n + 2);
        long totalCount = 0;
        
        for (int j = 0; j <= n; j++) {
            int currentVal = pref[j];
            int rank = lowerBound(sortedPref, currentVal);
            totalCount += ft.query(rank);
            
            int insertRank = upperBound(sortedPref, currentVal);
            ft.update(insertRank, 1);
        }
        
        return totalCount;
    }
    
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
    
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
    
    static class FenwickTree {
        private final int[] tree;
        private final int size;
        
        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }
        
        public void update(int index, int delta) {
            for (; index <= size; index += index & -index) {
                tree[index] += delta;
            }
        }
        
        public int query(int index) {
            int sum = 0;
            for (; index > 0; index -= index & -index) {
                sum += tree[index];
            }
            return sum;
        }
    }
}