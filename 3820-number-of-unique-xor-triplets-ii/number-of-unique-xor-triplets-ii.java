
class Solution {
    public int uniqueXorTriplets(int[] nums) {

        boolean[] pairXor = new boolean[2048];
        boolean[] tripletXor = new boolean[2048];

        // Step 1: Calculate all possible pair XOR values
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                pairXor[xor] = true;
            }
        }

        // Step 2: Combine every pair XOR with each array element
        for (int x = 0; x < 2048; x++) {

            if (pairXor[x]) {

                for (int num : nums) {
                    int xor = x ^ num;
                    tripletXor[xor] = true;
                }
            }
        }

        // Step 3: Count distinct triplet XOR values
        int count = 0;

        for (int i = 0; i < 2048; i++) {
            if (tripletXor[i]) {
                count++;
            }
        }

        return count;
    }
}
