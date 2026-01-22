class Solution {
    public int minOperations(int[] nums) {
        int[] stack = new int[nums.length + 1];
        int top = 0;
        int count = 0;
        for (int num : nums) {
            // Since previous number is larger, it has to be
            // processed separately
            while (stack[top] > num) {
                top--;
                count++;
            }

            // If num is 0 we don't need to add to stack because
            // it needs no processing and we don't need to
            // add duplicate
            if (num != 0 && (stack[top] != num)) {
                stack[++top] = num;
            } 
        }

        // If stack is [1, 2, 3, 4, 5] -> All numbers need
        // separate processing
        return count + top;
    }
}