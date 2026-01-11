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
    public boolean isValidBST(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }
                // Visited before
                if (predecessor.right == curr) {
                    if (curr.val <= prev.val) return false;
                    predecessor.right = null;
                    prev = curr;
                    curr = curr.right;
                } else {
                    predecessor.right = curr;
                    curr = curr.left;
                }
            } else {
                if (prev != null && curr.val <= prev.val) return false;
                prev = curr;
                curr = curr.right;
            }
        }
        return true;
    }

    // public boolean isValidBST(TreeNode root) {
    //     TreeNode curr = root;
    //     List<TreeNode> stack = new ArrayList<>();
    //     TreeNode prev = null;
    //     // inorder traversal for bst means values are strictly increasing
    //     while (curr != null || !stack.isEmpty()) {
    //         while (curr != null) {
    //             stack.add(curr);
    //             curr = curr.left;
    //         }
    //         curr = stack.remove(stack.size() - 1);
    //         if (prev != null && curr.val <= prev.val) return false;
    //         prev = curr;
    //         curr = curr.right;
    //         // if (curr != null) {
    //         //     stack.add(curr);
    //         //     curr = curr.left;
    //         // } else {
    //         //     curr = stack.remove(stack.size() - 1);
    //         //     if (prev != null && curr.val <= prev.val) return false;
    //         //     prev = curr;
    //         //     curr = curr.right;
    //         // }
    //     }
    //     return true;
    // }

    // public boolean isValidBST(TreeNode root) {
    //     return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    // }
    
    // private boolean isValidBST(TreeNode root, long lo, long hi) {
    //     if (root == null) return true;
    //     if (root.val <= lo || root.val >= hi) return false;
    //     return isValidBST(root.left, lo, root.val) && isValidBST(root.right, root.val, hi);
    // }
}