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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map inorder values to index since values are distinct
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, inorderMap, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preorderStart, Map<Integer, Integer> inorderMap, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        if (inorderStart == inorderEnd) {
            return new TreeNode(preorder[preorderStart]);
        }
        TreeNode node = new TreeNode(preorder[preorderStart]);
        int inorderIndex = inorderMap.get(node.val);

        int inorderLeftEnd = inorderIndex - 1;
        int numLeftNodes = inorderLeftEnd - inorderStart + 1;
        
        node.left = buildTree(preorder, preorderStart + 1, inorderMap, inorderStart, inorderLeftEnd);
        node.right = buildTree(preorder, preorderStart + numLeftNodes + 1, inorderMap, inorderIndex + 1, inorderEnd);
        return node;
    }
}