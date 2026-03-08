class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // Cantor's diagonalization
        // Since we want to create a unique binary string of length n,
        // and there are n strings in nums, we can just iterate over
        // each string in nums and append a different bit per string
        // to construct the binary string of length n
        // Since we know the string differs from each string in nums by
        // at least one bit, we know it is unique
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }

        return sb.toString();
    }
}