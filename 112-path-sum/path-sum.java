/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        // Case 1: empty tree
        if (root == null)
            return false;

        // Case 2: leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Remaining sum
        int remaining = targetSum - root.val;

        // Check left OR right subtree
        return hasPathSum(root.left, remaining) ||
               hasPathSum(root.right, remaining);
    }
}