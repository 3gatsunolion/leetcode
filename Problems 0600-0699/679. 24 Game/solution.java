class Solution {
    private final double eps = 0.001;

    public boolean judgePoint24(int[] cards) {
        double[] nums = new double[cards.length];
        for (int i = 0; i < cards.length; i++) {
            nums[i] = cards[i];
        }
        return backtrack(nums, cards.length);
    }

    private boolean backtrack(double[] nums, int n) {
        if (n == 1) {
            return Math.abs(nums[0] - 24.0) < eps;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                double b = nums[i], a = nums[j];
                // Move last element up to ith place so it's accessible
                // in next level in recursion
                nums[i] = nums[n - 1];

                nums[j] = a + b;
                if (backtrack(nums, n - 1)) return true;
                nums[j] = a - b;
                if (backtrack(nums, n - 1)) return true;
                nums[j] = b - a;
                if (backtrack(nums, n - 1)) return true;
                nums[j] = a * b;
                if (backtrack(nums, n - 1)) return true;
                if (Math.abs(a) > eps) {
                    nums[j] = b / a;
                    if (backtrack(nums, n - 1)) return true;
                }
                if (Math.abs(b) > eps) {
                    nums[j] = a / b;
                    if (backtrack(nums, n - 1)) return true;
                }

                // Backtrack
                nums[i] = b;
                nums[j] = a;
            }
        }
        return false;
    }

    // public boolean judgePoint24(int[] cards) {
    //     List<Double> nums = new ArrayList();
    //     for (int card : cards) {
    //         nums.add((double) card);
    //     }
    //     return backtrack(nums);
    // }

    // private boolean backtrack(List<Double> nums) {
    //     if (nums.size() == 1) {
    //         if (Math.abs(nums.get(0) - 24.0) < eps) {
    //             return true;
    //         }
    //         return false;
    //     }

    //     // Pick any two numbers, a and b, and apply any operator +, -, *, / ,
    //     // assuming that the expression is surrounded with parenthesis (a + b)
    //     for (int i = 0; i < nums.size(); i++) {
    //         for (int j = 0; j < i; j++) {
    //             double a = nums.get(i), b = nums.get(j);

    //             List<Double> vals = new ArrayList(Arrays.asList(
    //                 a + b,
    //                 a - b,
    //                 b - a, // try different orders of a and b
    //                 a * b
    //             ));
    //             // If <= eps, then it's probably 0, which would
    //             // be invalid dividing by 0
    //             if (Math.abs(a) > eps) {
    //                 vals.add(b / a);
    //             }
    //             if (Math.abs(b) > eps) {
    //                 vals.add(a / b);
    //             }

    //             List<Double> numsNextLevel = new ArrayList();
    //             for (int k = 0; k < nums.size(); k++) {
    //                 if (k == i || k == j) continue;
    //                 numsNextLevel.add(nums.get(k));
    //             }

    //             for (double val : vals) {
    //                 numsNextLevel.add(val);
    //                 if (backtrack(numsNextLevel)) return true;
    //                 numsNextLevel.remove(numsNextLevel.size() - 1);
    //             }
    //         }
    //     }
    //     return false;
    // }
}