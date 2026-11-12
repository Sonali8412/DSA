class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Use standard integer-to-long casting to avoid overflow during multiplications
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // If 'costBoth' is more expensive than buying type 1 and type 2 individually,
        // we should cap the effective costBoth to cost1 + cost2.
        cb = Math.min(cb, c1 + c2);

        // We can satisfy min(n1, n2) units of both requirements simultaneously using type 3 items.
        long common = Math.min(n1, n2);
        long totalCost = common * Math.min(cb, c1 + c2);

        // Remaining needs after fulfilling the common part
        long rem1 = n1 - common;
        long rem2 = n2 - common;

        // For the remaining type 1 requirements, use the cheaper option between type 1 and type 3
        totalCost += rem1 * Math.min(c1, cb);

        // For the remaining type 2 requirements, use the cheaper option between type 2 and type 3
        totalCost += rem2 * Math.min(c2, cb);

        return totalCost;
    }
}