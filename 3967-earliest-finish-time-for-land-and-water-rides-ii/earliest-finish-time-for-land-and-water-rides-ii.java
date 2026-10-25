import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;
        
        Integer[] landIdx = new Integer[n];
        for (int i = 0; i < n; i++) landIdx[i] = i;
        Arrays.sort(landIdx, Comparator.comparingInt(i -> landStartTime[i]));
        
        Integer[] waterIdx = new Integer[m];
        for (int j = 0; j < m; j++) waterIdx[j] = j;
        Arrays.sort(waterIdx, Comparator.comparingInt(j -> waterStartTime[j]));
        
        int[] sortedLandStart = new int[n];
        int[] sortedLandDur = new int[n];
        for (int i = 0; i < n; i++) {
            sortedLandStart[i] = landStartTime[landIdx[i]];
            sortedLandDur[i] = landDuration[landIdx[i]];
        }
        
        int[] sortedWaterStart = new int[m];
        int[] sortedWaterDur = new int[m];
        for (int j = 0; j < m; j++) {
            sortedWaterStart[j] = waterStartTime[waterIdx[j]];
            sortedWaterDur[j] = waterDuration[waterIdx[j]];
        }
        
        long minFinish = Long.MAX_VALUE;
        
        // Order 1: Land -> Water
        minFinish = Math.min(minFinish, solve(sortedLandStart, sortedLandDur, sortedWaterStart, sortedWaterDur));
        // Order 2: Water -> Land
        minFinish = Math.min(minFinish, solve(sortedWaterStart, sortedWaterDur, sortedLandStart, sortedLandDur));
        
        return (int) minFinish;
    }
    
    private long solve(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int n = start1.length;
        int m = start2.length;
        
        long[] sufMinFinish2 = new long[m + 1];
        sufMinFinish2[m] = Long.MAX_VALUE;
        for (int j = m - 1; j >= 0; j--) {
            sufMinFinish2[j] = Math.min(sufMinFinish2[j + 1], (long) start2[j] + dur2[j]);
        }
        
        int[] prefMinDur2 = new int[m + 1];
        prefMinDur2[0] = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            prefMinDur2[j + 1] = Math.min(prefMinDur2[j], dur2[j]);
        }
        
        long minFinish = Long.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            long finish1 = (long) start1[i] + dur1[i];
            
            // Find first element in start2 where start2[j] > finish1
            int idx = upperBound(start2, (int) finish1);
            
            // Case A: start2[j] <= finish1 (can start immediately at finish1)
            if (idx > 0) {
                long currentFinish = finish1 + prefMinDur2[idx];
                minFinish = Math.min(minFinish, currentFinish);
            }
            
            // Case B: start2[j] > finish1 (must wait until start2[j])
            if (idx < m) {
                long currentFinish = sufMinFinish2[idx];
                minFinish = Math.min(minFinish, currentFinish);
            }
        }
        
        return minFinish;
    }
    
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}