import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCost = new long[n - x + 1];
        
        PriorityQueue<Long> left = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap
        PriorityQueue<Long> right = new PriorityQueue<>(); // Min-heap
        
        Map<Long, Integer> leftDelayed = new HashMap<>();
        Map<Long, Integer> rightDelayed = new HashMap<>();
        
        // state[0] = leftSize, state[1] = rightSize, state[2] = leftSum, state[3] = rightSum
        long[] state = new long[4];
        
        // Initialize the first window
        List<Long> initialWindow = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            initialWindow.add((long) nums[i]);
        }
        Collections.sort(initialWindow);
        
        for (int i = 0; i < x; i++) {
            if (i < (x + 1) / 2) {
                left.offer(initialWindow.get(i));
                state[0]++;
                state[2] += initialWindow.get(i);
            } else {
                right.offer(initialWindow.get(i));
                state[1]++;
                state[3] += initialWindow.get(i);
            }
        }
        
        cleanLeft(left, leftDelayed);
        long median = left.peek();
        windowCost[0] = (state[0] * median - state[2]) + (state[3] - state[1] * median);
        
        // Slide the window
        for (int i = 1; i <= n - x; i++) {
            long outgoing = nums[i - 1];
            long incoming = nums[i + x - 1];
            
            cleanLeft(left, leftDelayed);
            if (outgoing <= left.peek()) {
                leftDelayed.put(outgoing, leftDelayed.getOrDefault(outgoing, 0) + 1);
                state[0]--;
                state[2] -= outgoing;
            } else {
                rightDelayed.put(outgoing, rightDelayed.getOrDefault(outgoing, 0) + 1);
                state[1]--;
                state[3] -= outgoing;
            }
            balance(left, right, leftDelayed, rightDelayed, state);
            
            cleanLeft(left, leftDelayed);
            if (incoming <= left.peek()) {
                left.offer(incoming);
                state[0]++;
                state[2] += incoming;
            } else {
                right.offer(incoming);
                state[1]++;
                state[3] += incoming;
            }
            balance(left, right, leftDelayed, rightDelayed, state);
            
            cleanLeft(left, leftDelayed);
            median = left.peek();
            windowCost[i] = (state[0] * median - state[2]) + (state[3] - state[1] * median);
        }
        
        // Step 2: DP to find k non-overlapping windows of size x
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
            dp[i][0] = 0;
        }
        
        for (int j = 1; j <= k; j++) {
            long minPrev = Long.MAX_VALUE / 2;
            for (int i = 1; i <= n; i++) {
                if (i >= x) {
                    long costAtWindow = windowCost[i - x];
                    minPrev = Math.min(minPrev, dp[i - x][j - 1]);
                    dp[i][j] = Math.min(dp[i - 1][j], minPrev + costAtWindow);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][k];
    }
    
    private void cleanLeft(PriorityQueue<Long> left, Map<Long, Integer> leftDelayed) {
        while (!left.isEmpty() && leftDelayed.getOrDefault(left.peek(), 0) > 0) {
            long val = left.poll();
            leftDelayed.put(val, leftDelayed.get(val) - 1);
        }
    }
    
    private void cleanRight(PriorityQueue<Long> right, Map<Long, Integer> rightDelayed) {
        while (!right.isEmpty() && rightDelayed.getOrDefault(right.peek(), 0) > 0) {
            long val = right.poll();
            rightDelayed.put(val, rightDelayed.get(val) - 1);
        }
    }
    
    private void balance(PriorityQueue<Long> left, PriorityQueue<Long> right, 
                         Map<Long, Integer> leftDelayed, Map<Long, Integer> rightDelayed, long[] state) {
        cleanLeft(left, leftDelayed);
        cleanRight(right, rightDelayed);
        
        while (state[0] > state[1] + 1) {
            cleanLeft(left, leftDelayed);
            long val = left.poll();
            state[0]--;
            state[2] -= val;
            right.offer(val);
            state[1]++;
            state[3] += val;
            cleanLeft(left, leftDelayed);
        }
        while (state[1] > state[0]) {
            cleanRight(right, rightDelayed);
            long val = right.poll();
            state[1]--;
            state[3] -= val;
            left.offer(val);
            state[0]++;
            state[2] += val;
            cleanRight(right, rightDelayed);
        }
    }
}