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
        bool hasPathSum(TreeNode* root, int targetSum) {
            return hasPath(root, 0, targetSum);
        }
    
        bool hasPath(TreeNode* root, int currSum, int& targetSum) {
            if (!root) return false;
    
            currSum += root->val;
            if (!root->left && !root->right) {
                return currSum == targetSum;
            }
    
            return hasPath(root->left, currSum, targetSum) || hasPath(root->right, currSum, targetSum);
        }
    };