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
    // public TreeNode subtreeWithAllDeepest(TreeNode root) {
    //     // Basically common ancestor of the deepest nodes
    //     // Get max depth
    //     int maxDepth = getMaxDepth(root);
    //     return dfs(root, 0, maxDepth);
    // }

    // private TreeNode dfs(TreeNode node, int depth, int maxDepth) {
    //     if (node == null) return node;
    //     if (depth == maxDepth) return node;

    //     TreeNode left = dfs(node.left, depth + 1, maxDepth);
    //     TreeNode right = dfs(node.right, depth + 1, maxDepth);
    //     if (left != null && right != null) return node;
    //     return (left != null ? left : right);
    // }

    // private int getMaxDepth(TreeNode node) {
    //     if (node == null) return -1;
    //     int left = getMaxDepth(node.left);
    //     int right = getMaxDepth(node.right);
    //     return 1 + Math.max(left, right);
    // }

    // One pass
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // Basically common ancestor of the deepest nodes
        return lca(root).getValue();
    }

    private Pair<Integer, TreeNode> lca(TreeNode node) {
        if (node == null) return new Pair(0, null);

        Pair<Integer, TreeNode> l = lca(node.left);
        Pair<Integer, TreeNode> r = lca(node.right);

        int lDepth = l.getKey();
        int rDepth = r.getKey();

        if (lDepth == rDepth) {
            return new Pair(1 + lDepth, node);
        } else if (lDepth > rDepth) {
            return new Pair(1 + lDepth, l.getValue());
        } else {
            return new Pair(1 + rDepth, r.getValue());
        }
    }
}