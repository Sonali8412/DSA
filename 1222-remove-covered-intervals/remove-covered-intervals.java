import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort intervals: by starting point ascending; 
        // if starting points are equal, by ending point descending.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            // If the current interval's end is greater than maxEnd, 
            // it is not covered, so we keep it and update maxEnd.
            if (interval[1] > maxEnd) {
                maxEnd = interval[1];
                count++;
            }
        }

        return count;
    }
}