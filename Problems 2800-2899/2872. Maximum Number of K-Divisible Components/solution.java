class Solution {
    private int ans = 0;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        ans = 0;
        dfs(graph, values, 0, -1, k);
        return ans;
    }

    private long dfs(List<Integer>[] graph, int[] values, int node, int parent, int k) {
        // Greedy approach -> n nodes with n - 1 edges, meaning
        // there is only one path between one node a to another
        // node b. Since sum(values) % k == 0, we know that
        // if there's a subcomponent that is divisible by k,
        // then we can just remove it and the other sums
        // will still be divisible by k, otherwise that
        // breaks the constraint that sum(values) % k == 0.
        // So we just traverse each node through the graph
        // Time complexity: O(n)
        // Space complexity: O(n)
        long sum = values[node];
        for (int adj : graph[node]) {
            if (adj != parent) {
                sum += dfs(graph, values, adj, node, k);
            }
        }

        if (sum % k == 0) {
            ans++;
            return 0;
        }

        return sum;
    }
}