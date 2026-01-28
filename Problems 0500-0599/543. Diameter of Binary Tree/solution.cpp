/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
 class Solution {
    public:
        int diameterOfBinaryTree(TreeNode* root) {
            int res = 0;
            getDiameter(root, res);
            return res;
        }
    
        int getDiameter(TreeNode* node, int& res) {
            if (!node) return 0;
    
            int leftDepth = getDiameter(node->left, res);
            int rightDepth = getDiameter(node->right, res);
            
            res = max(res, leftDepth + rightDepth);
            return 1 + max(leftDepth, rightDepth);
        }
    };