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
    // private int count = 0;
    // private int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.right;
        }

        return -1;

        // count = 0;
        // res = 0;
        // findKth(root, k);
        // return res;
    }

    // private void findKth(TreeNode root, int k) {
    //     if (root == null) return;

    //     findKth(root.left, k);
    //     count++;

    //     if (count == k) {
    //         res = root.val;
    //         return;
    //     }

    //     if (count < k) {
    //         findKth(root.right, k);
    //     }
    // }
}