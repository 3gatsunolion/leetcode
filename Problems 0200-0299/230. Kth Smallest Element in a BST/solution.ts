/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function kthSmallest(root: TreeNode | null, k: number): number {
    let count = 0;
    let res = 0;
    function solve(root: TreeNode | null, k: number) {
        if (!root) return;

        solve(root.left, k);
        count++;

        if (count === k) {
            res = root.val;
            return;
        }

        if (count > k) return;

        solve(root.right, k);
    }

    solve(root, k);
    return res;
};