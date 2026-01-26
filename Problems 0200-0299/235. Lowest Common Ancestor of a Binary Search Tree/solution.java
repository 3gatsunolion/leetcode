/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (true) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            } else {
                break;
            }
        }
        return curr;
        // if (p.val < root.val && q.val < root.val) {
        //     return lowestCommonAncestor(root.left, p, q);
        // } else if (root.val < p.val && root.val < q.val) {
        //     return lowestCommonAncestor(root.right, p, q);
        // } else {
        //     return root;
        // }
    }
}