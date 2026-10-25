class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;
        
        int n = landStartTime.length;
        int m = waterStartTime.length;
        
        // Option 1: Land ride first, then Water ride
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];
            for (int j = 0; j < m; j++) {
                int waterStart = Math.max(landFinish, waterStartTime[j]);
                int waterFinish = waterStart + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, waterFinish);
            }
        }
        
        // Option 2: Water ride first, then Land ride
        for (int j = 0; j < m; j++) {
            int waterFinish = waterStartTime[j] + waterDuration[j];
            for (int i = 0; i < n; i++) {
                int landStart = Math.max(waterFinish, landStartTime[i]);
                int landFinish = landStart + landDuration[i];
                minFinishTime = Math.min(minFinishTime, landFinish);
            }
        }
        
        return minFinishTime;
    }
}