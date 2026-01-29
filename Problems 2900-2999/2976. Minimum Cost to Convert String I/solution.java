class Solution {
    // // 1. Dijkstra -> Not optimal because dijkstra is good when you
    // // want optimal path from u to v, not when you want
    // // shortest path between all (or most) pairs
    // // Time: O(N*ElogV) -> V is 26 (number of nodes), E is original.length,
    // // N is source.length
    // // Space: O(V + E)
    // public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
    //     long[][] minCosts = new long[26][26];

    //     for (int i = 0; i < 26; i++) {
    //         for (int j = 0; j < 26; j++) {
    //             // cost from i -> i is 0
    //             if (i != j) {
    //                 minCosts[i][j] = Long.MAX_VALUE;
    //             }
    //         }
    //     }

    //     List<List<long[]>> graph = new ArrayList<>();
    //     for (int i = 0; i < 26; i++) {
    //         graph.add(new ArrayList<>());
    //     }

    //     for (int i = 0; i < original.length; i++) {
    //         char u = original[i];
    //         char v = changed[i];
    //         graph.get(u - 'a').add(new long[] {v - 'a', cost[i]});
    //     }

    //     long res = 0;
    //     for (int i = 0; i < source.length(); i++) {
    //         int u = source.charAt(i) - 'a';
    //         int v = target.charAt(i) - 'a';
    //         if (minCosts[u][v] == Long.MAX_VALUE) {
    //             // dijkstra
    //             Queue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
    //             q.add(new long[] {0, u}); // cost, charId

    //             boolean isPossible = false;
    //             while (!q.isEmpty()) {
    //                 long[] curr = q.poll();
    //                 long currCost = curr[0];
    //                 int charId = (int) curr[1];

    //                 if (charId == v) {
    //                     isPossible = true;
    //                     // IMPORTANT: CANNOT EXIT EARLY!!
    //                     // because the minCosts will potentially
    //                     // not be accurate for other pairs
    //                     // And if we need the minCost of a pair
    //                     // u, v -> minCosts[u][v] == Long.MAX_VALUE
    //                     // will not run and the cost will be inaccurate
    //                     // break;
    //                 }

    //                 if (minCosts[u][charId] < currCost) continue;

    //                 for (long[] adj : graph.get(charId)) {
    //                     int nextNode = (int) adj[0];
    //                     long addCost = adj[1];
    //                     long newCost = currCost + addCost;

    //                     if (minCosts[u][nextNode] > newCost) {
    //                         minCosts[u][nextNode] = newCost;
    //                         q.add(new long[] {newCost, nextNode});
    //                     }
    //                 }
    //             }

    //             if (!isPossible) return -1;
    //         }
    //         res += minCosts[u][v];
    //     }

    //     return res;
    // }

    // 2. Floyd-Warshall Algorithm -> all-pairs shortest path algorithm
    // Time complexity -> optimization part doesn't depend on number
    // of edges, just nodes
    // O(V^3 + E + N) -> V is 26 (number of nodes), E is original.length,
    // N is source.length
    // Space: O(26*26) -> O(1)
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] minCosts = new long[26][26];

        for (int i = 0; i < 26; i++) {
            Arrays.fill(minCosts[i], Long.MAX_VALUE);
            minCosts[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            minCosts[from][to] = Math.min(minCosts[from][to], cost[i]);
        }

        // IMPORTANT: k (intermediate step) must be outer loop
        // Order of inner two loops doesn't matter
        // This is because of each iteration of k, we want to 
        // calculate dp(k)[i][j] = min(dp(k-1)[i][k], dp(k-1)[k][j])
        // where dp(k)[i][j] means shortest path between i and j
        // using only intermediate steps 0,...,k
        // So we need k in the outer loop so we have calculated k - 1
        // for ALL pairs (i, j), so we know dp(k-1)[i][k] and dp(k-1)[k][j]
        // are calculated
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                if (minCosts[i][k] == Long.MAX_VALUE) continue;
                for (int j = 0; j < 26; j++) {
                    if (minCosts[k][j] != Long.MAX_VALUE) {
                        minCosts[i][j] = Math.min(minCosts[i][j], minCosts[i][k] + minCosts[k][j]);
                    }
                }
            }
        }

        long res = 0;
        for (int i = 0; i < source.length(); i++) {
            int u = source.charAt(i) - 'a';
            int v = target.charAt(i) - 'a';

            if (minCosts[u][v] == Long.MAX_VALUE) {
                return -1;
            }

            res += minCosts[u][v];
        }

        return res;
    }
}