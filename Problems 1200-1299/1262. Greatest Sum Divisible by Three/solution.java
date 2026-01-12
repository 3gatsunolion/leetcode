class Solution {
    public int maxSumDivThree(int[] nums) {
        // int[] maxSum = new int[3];
        // int res = 0;
        // for (int num : nums) {
        //     for (int sum : Arrays.copyOf(maxSum, maxSum.length)) {
        //         int currSum = sum + num;
        //         maxSum[currSum % 3] = Math.max(maxSum[currSum % 3], currSum);
        //     }
        // }
        // return maxSum[0];

        int minOne = -1;
        int minTwo = -1;
        int sum = 0;

        for (int num : nums) {
            sum += num;

            if ((num % 3) == 1) {
                if (minOne != -1) {
                    if (minTwo == -1) {
                        minTwo = num + minOne;
                    } else {
                        minTwo = Math.min(minTwo, num + minOne);
                    }
                    minOne = Math.min(minOne, num);
                } else {
                    minOne = num;
                }
            }

            if ((num % 3) == 2) {
                if (minTwo != -1) {
                    if (minOne == -1) {
                        minOne = num + minTwo;
                    } else {
                        minOne = Math.min(minOne, num + minTwo);
                    }
                    minTwo = Math.min(minTwo, num);
                } else {
                    minTwo = num;
                }
            }
        }

        if (sum % 3 == 0) return sum;

        if (sum % 3 == 1 && minOne != -1) return sum - minOne;

        if (sum % 3 == 2 && minTwo != -1) return sum - minTwo;

        return 0;
    }
}