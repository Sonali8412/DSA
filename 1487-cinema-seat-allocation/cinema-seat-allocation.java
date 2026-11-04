import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> rowReserved = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            rowReserved.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }
        
        // Start with max possible allocation (2 groups per row)
        int totalGroups = 2 * n;
        
        for (Map.Entry<Integer, Set<Integer>> entry : rowReserved.entrySet()) {
            Set<Integer> seats = entry.getValue();
            
            boolean leftFree = !seats.contains(2) && !seats.contains(3) && !seats.contains(4) && !seats.contains(5);
            boolean rightFree = !seats.contains(6) && !seats.contains(7) && !seats.contains(8) && !seats.contains(9);
            boolean middleFree = !seats.contains(4) && !seats.contains(5) && !seats.contains(6) && !seats.contains(7);
            
            if (leftFree && rightFree) {
                // Both blocks free -> 0 reduction
                continue;
            } else if (leftFree || rightFree || middleFree) {
                // At least one block free -> lose 1 allocation
                totalGroups -= 1;
            } else {
                // No block free -> lose 2 allocations
                totalGroups -= 2;
            }
        }
        
        return totalGroups;
    }
}