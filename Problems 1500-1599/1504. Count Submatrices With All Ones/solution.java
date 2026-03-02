class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] heights = new int[n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                heights[j] = mat[i][j] == 1 ? (heights[j] + 1) : 0;
            }
            res += countSubmat(heights);
        }
        return res;
    }

    private int countSubmat(int[] heights) {
        int n = heights.length;

        int[] dp = new int[n];
        int[] stack = new int[n];

        int res = 0;
        int i = -1;
        for (int right = 0; right < n; right++) {
            while (i >= 0 && heights[stack[i]] >= heights[right]) {
                i--;
            }

            int h = heights[right];
            int w = right - (i == -1 ? -1 : stack[i]);
            dp[right] = w * h + (i == -1 ? 0 : dp[stack[i]]);
            res += dp[right];

            stack[++i] = right;
        }

        return res;

        // int n = heights.length;
        // int res = 0;

        // // dp[i]: Number of submatrices ending at column i
        // int[] dp = new int[n];
        // Stack<Integer> stack = new Stack<>();

        // for (int i = 0; i < n; i++) {
        //     while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
        //         stack.pop();
        //     }
            
        //     int h = heights[i];
        //     int w = i - (stack.isEmpty() ? -1 : stack.peek());
        //     dp[i] = w * h + (stack.isEmpty() ? 0 : dp[stack.peek()]);
        //     res += dp[i];

        //     stack.push(i);
        // }
        // return res;
    }
}