class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;
        for (int num : numSet) {
            if (numSet.contains(num - 1)) {
                continue;
            }

            int count = 1;
            while (numSet.contains(num + count)) {
                count++;
            }

            if (count > longest) {
                longest = count;
            }
        }
        return longest;
    }
}