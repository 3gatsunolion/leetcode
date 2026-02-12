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
    private final int MOD = (int) Math.pow(10, 9) + 7;
    private long res = 0;
    public int maxProduct(TreeNode root) {
        long totalSum = getTreeSum(root);
        getMaxProduct(root, totalSum);

        return (int) (res % MOD);
    }

    private long getMaxProduct(TreeNode root, long totalSum) {
        if (root == null) return 0;

        long left = getMaxProduct(root.left, totalSum);
        long right = getMaxProduct(root.right, totalSum);

        long subTreeSum = root.val + left + right;
        long product = subTreeSum * (totalSum - subTreeSum);

        if (product > res) {
            res = product;
        }

        return subTreeSum;
    }

    private long getTreeSum(TreeNode node) {
        if (node == null) return 0;

        return node.val + getTreeSum(node.left) + getTreeSum(node.right);
    }
}