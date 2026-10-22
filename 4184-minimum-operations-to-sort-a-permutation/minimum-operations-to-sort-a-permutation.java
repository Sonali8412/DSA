import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        // Check if nums is a rotation of increasing order or decreasing order
        int[] inc = new int[n];
        int[] dec = new int[n];
        for (int i = 0; i < n; i++) {
            inc[i] = i;
            dec[i] = n - 1 - i;
        }

        // Helper to check if array `a` is a cyclic rotation of array `b`
        // Returns the shift amount if true, or -1 otherwise
        int incShift = getRotationShift(nums, inc);
        int decShift = getRotationShift(nums, dec);

        if (incShift == -1 && decShift == -1) {
            return -1;
        }

        // BFS to find the minimum operations from (type=0, shift=0)
        // State representation: node = type * n + shift
        // type 0: rotation of original, type 1: rotation of reversed
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2][n];

        // Start state: (0, 0)
        queue.offer(new int[]{0, 0, 0}); // {type, shift, dist}
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int type = curr[0];
            int shift = curr[1];
            int dist = curr[2];

            // Check if current state matches target
            if (type == 0 && shift == incShift) return dist;
            if (type == 1 && shift == decShift) return dist;

            // Operation 1: Rotate Left by One
            int nextType1 = type;
            int nextShift1 = (shift + 1) % n;
            if (!visited[nextType1][nextShift1]) {
                visited[nextType1][nextShift1] = true;
                queue.offer(new int[]{nextType1, nextShift1, dist + 1});
            }

            // Operation 2: Reverse the entire array
            int nextType2 = 1 - type;
            int nextShift2 = (n - shift) % n;
            if (!visited[nextType2][nextShift2]) {
                visited[nextType2][nextShift2] = true;
                queue.offer(new int[]{nextType2, nextShift2, dist + 1});
            }
        }

        return -1;
    }

    private int getRotationShift(int[] nums, int[] target) {
        int n = nums.length;
        // Find where target[0] appears in nums
        int startIdx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == target[0]) {
                startIdx = i;
                break;
            }
        }

        if (startIdx == -1) return -1;

        // Verify if it forms a valid cyclic rotation
        for (int i = 0; i < n; i++) {
            if (nums[(startIdx + i) % n] != target[i]) {
                return -1;
            }
        }
        return startIdx;
    }
}