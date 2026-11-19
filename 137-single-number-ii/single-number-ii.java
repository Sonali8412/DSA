class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;

        for (int num : nums) {
            // Add bits appearing a second time to `twos`, then remove bits appearing a third time
            twos |= ones & num;
            ones ^= num;

            // Mask out bits that have appeared 3 times (present in both `ones` and `twos`)
            int threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }

        return ones;
    }
}