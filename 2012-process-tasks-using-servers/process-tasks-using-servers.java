import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for available servers: sorted by weight, then by index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Priority queue for busy servers: sorted by free time, then server weight, then server index
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            // {weight, index}
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // If no servers are free, fast-forward time to when the earliest server becomes free
            if (freeServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Move all servers that have finished their tasks back to the free pool
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] busy = busyServers.poll();
                freeServers.offer(new int[] { busy[1], busy[2] }); // {weight, index}
            }

            // Assign the current task to the best available server
            int[] server = freeServers.poll();
            ans[j] = server[1];
            
            // {free_time, weight, index}
            busyServers.offer(new int[] { (int) currentTime + tasks[j], server[0], server[1] });
        }

        return ans;
    }
}