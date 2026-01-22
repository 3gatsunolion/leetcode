class Solution {
    // Time complexity: O(n * budget * budget) -> traverse each node
    // once and each node merge operation is budget * budget
    // Space complexity: O(n * budget) -> Recursion, at most will be
    // n (for the n nodes) and at each step we keep array of length budget
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        // Build graph
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : hierarchy) {
            graph[edge[0] - 1].add(edge[1] - 1);
        }

        // Start at root (boss)
        int[] res = dfs(0, present, future, graph, budget)[0];

        int maxProfit = 0;
        for (int p : res) {
            maxProfit = Math.max(maxProfit, p);
        }
        return maxProfit;
    }

    private int[][] dfs(int curr, int[] present, int[] future, List<Integer>[] graph, int budget) {
        int[] dp_curr_not_buying = new int[budget + 1];
        int[] dp_curr_buying = new int[budget + 1];

        Arrays.fill(dp_curr_not_buying, -1);
        Arrays.fill(dp_curr_buying, -1);

        // Base case, zero budget gives max 0 profit
        dp_curr_not_buying[0] = 0;
        dp_curr_buying[0] = 0;

        // Guaranteed to have no cycles, all nodes are part of one tree
        for (int adj : graph[curr]) {
            int[][] child = dfs(adj, present, future, graph, budget);

            // Merge child results to get max profit with just children
            // of curr
            dp_curr_not_buying = merge(dp_curr_not_buying, child[0], budget);
            dp_curr_buying = merge(dp_curr_buying, child[1], budget);
        }

        int[] dp_parent_not_buying = new int[budget + 1];
        int[] dp_parent_buying = new int[budget + 1];

        int costFull = present[curr];
        int costHalf = costFull / 2;
        int profitFull = future[curr] - costFull;
        int profitHalf = future[curr] - costHalf;

        for (int b = 0; b <= budget; b++) {
            // Since parent of curr has no effect on children of curr
            // (has to be direct boss), so we can just max dp_curr_not_buying
            // values
            if (dp_curr_not_buying[b] != -1) {
                dp_parent_not_buying[b] = Math.max(dp_parent_not_buying[b],
                                                   dp_curr_not_buying[b]);
                dp_parent_buying[b] = Math.max(dp_parent_buying[b],
                                               dp_curr_not_buying[b]);
            }
            if (dp_curr_buying[b] != -1) {
                if ((b + costFull) <= budget) {
                    dp_parent_not_buying[b + costFull] = Math.max(dp_parent_not_buying[b + costFull], dp_curr_buying[b] + profitFull);
                }

                if ((b + costHalf) <= budget) {
                    dp_parent_buying[b + costHalf] = Math.max(dp_parent_buying[b + costHalf], dp_curr_buying[b] + profitHalf);
                }
            }
        }

        return new int[][] {dp_parent_not_buying, dp_parent_buying};
    }

    private int[] merge(int[] dp1, int[] dp2, int budget) {
        int[] dp = new int[budget + 1];

        for (int b1 = 0; b1 <= budget; b1++) {
            if (dp1[b1] == -1) continue;
            int limit = budget - b1;
            for (int b2 = 0; b2 <= limit; b2++) {
                if (dp2[b2] == -1) continue;
                dp[b1 + b2] = Math.max(dp[b1 + b2], dp1[b1] + dp2[b2]);
            }
        }

        return dp;
    }
}
