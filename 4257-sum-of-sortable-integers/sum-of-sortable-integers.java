class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n + 1];

        prefMax[0] = 0;
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        for (int i = 1; i < n; i++) {
            prefDrop[i + 1] = prefDrop[i]
                    + (nums[i - 1] > nums[i] ? 1 : 0);
        }

        prefDrop[1] = 0;

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                int end = start + k - 1;

                int drops = prefDrop[end + 1] - prefDrop[start + 1];

                if (nums[end] > nums[start]) {
                    drops++;
                }

                if (drops > 1 || prefMax[start] > suffMin[start]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}