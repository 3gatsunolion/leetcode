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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        return root;
    }

    // public TreeNode invertTree(TreeNode root) {
    //     if (root == null) {
    //         return null;
    //     }

    //     Deque<TreeNode> stack = new LinkedList<>();
    //     stack.push(root);

    //     while (!stack.isEmpty()) {
    //         TreeNode node = stack.pop();

    //         TreeNode tmp = node.left;
    //         node.left = node.right;
    //         node.right = tmp;

    //         if (node.left != null) stack.push(node.left);
    //         if (node.right != null) stack.push(node.right);
    //     }

    //     return root;
    // }

    // public TreeNode invertTree(TreeNode root) {
    //     if (root == null) return root;
    //     TreeNode left = invertTree(root.left);
    //     TreeNode right = invertTree(root.right);
    //     root.left = right;
    //     root.right = left;
    //     return root;
    // }
}