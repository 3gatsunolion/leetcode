class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            // greedy solution, if i is 0, the only way to turn
            // it 1 is to flip i, i+1, i+2
            if (nums[i] == 0) {
                count++;
                nums[i+1] = !nums[i+1];
                nums[i+2] = !nums[i+2];
            }
        }
        return nums[n-1] && nums[n-2] ? count : -1;
    }
};