class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rectangle 1 is to the left, right, below, or above rectangle 2 completely
        boolean overlapX = rec1[0] < rec2[2] && rec2[0] < rec1[2];
        boolean overlapY = rec1[1] < rec2[3] && rec2[1] < rec1[3];
        
        return overlapX && overlapY;
    }
}