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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        
        Deque<TreeNode> stack = new LinkedList<>();

        stack.offer(p);
        stack.offer(q);
        while (!stack.isEmpty()) {
            TreeNode qNode = stack.pop();
            TreeNode pNode = stack.pop();

            if (pNode == null && qNode == null) continue;
            if (pNode == null || qNode == null) return false;
            if (pNode.val != qNode.val) return false;

            stack.push(pNode.left);
            stack.push(qNode.left);
            stack.push(pNode.right);
            stack.push(qNode.right);
        }

        return true;
    }

    // public boolean isSameTree(TreeNode p, TreeNode q) {
    //     if (p == null || q == null) return p == q;
    //     if (p.val != q.val) return false;
    //     return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    // }
}