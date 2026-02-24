class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        // Note: (ab + c)%d is same as ((a%d)(b%d) + c%d)%d.
        // To prevent overflow
        int n = nums.length;
        List<Boolean> answer = new ArrayList<>();
        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr = ((curr << 1) | nums[i]) % 5;
            answer.add((curr == 0) ? true : false);
        }
        return answer;
    }
}