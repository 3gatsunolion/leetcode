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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        return levelOrder(root, 0, res);
    }

    private List<List<Integer>> levelOrder(TreeNode node, int level, List<List<Integer>> res) {
        if (node == null) return res;
        if (res.size() == level) {
            res.add(new ArrayList());
        }
        res.get(level).add(node.val);
        levelOrder(node.left, level + 1, res);
        levelOrder(node.right, level + 1, res);
        return res;
    }

    // public List<List<Integer>> levelOrder(TreeNode root) {
    //     List<List<Integer>> res = new ArrayList();

    //     if (root == null) return res;

    //     Queue<TreeNode> q = new LinkedList<>();
    //     q.offer(root);
    //     while (!q.isEmpty()) {
    //         int n = q.size();
    //         List<Integer> level = new ArrayList();
    //         for (int i = 0; i < n; i++) {
    //             TreeNode node = q.poll();
    //             level.add(node.val);

    //             if (node.left != null) q.offer(node.left);
    //             if (node.right != null) q.offer(node.right);
    //         }
    //         res.add(level);
    //     }
    //     return res;
    // }
}