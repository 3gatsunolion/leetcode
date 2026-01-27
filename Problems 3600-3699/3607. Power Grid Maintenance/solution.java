class UnionFind {
    private int[] parent;
    private int[] rank;

    UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            int tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }

        parent[rootY] = rootX;
        if (rank[rootX] == rank[rootY]) {
            rank[rootX]++;
        }
    }
};

class Solution {
    // // Union Find
    // public int[] processQueries(int c, int[][] connections, int[][] queries) {
    //     UnionFind uf = new UnionFind(c);
    //     for (int[] cn : connections) {
    //         uf.union(cn[0], cn[1]);
    //     }

    //     boolean[] online = new boolean[c + 1];
    //     Map<Integer, PriorityQueue<Integer>> heaps = new HashMap<>();
    //     for (int station = 1; station <= c; station++) {
    //         int root = uf.find(station);
    //         if (!heaps.containsKey(root)) {
    //             heaps.put(root, new PriorityQueue<>());
    //         }
    //         heaps.get(root).add(station);
    //         online[station] = true;
    //     }

    //     // List<Integer> res = new ArrayList<>();
    //     int[] res = new int[queries.length];
    //     int i = 0;
    //     for (int[] query : queries) {
    //         int type = query[0];
    //         int x = query[1];

    //         if (type == 2) {
    //             online[x] = false;
    //         } else {
    //             if (online[x]) {
    //                 // res.add(x);
    //                 res[i++] = x;
    //                 continue;
    //             }

    //             int root = uf.find(x);

    //             PriorityQueue<Integer> pq = heaps.get(root);
    //             while (!pq.isEmpty() && !online[pq.peek()]) {
    //                 pq.poll();
    //             }

    //             // res.add(pq.isEmpty() ? -1 : pq.peek());
    //             res[i++] = pq.isEmpty() ? -1 : pq.peek();
    //         }
    //     }

    //     // return res.stream()
    //     //           .mapToInt(Integer::intValue) // or .mapToInt(i -> i) due to autoboxing
    //     //           .toArray();
    //     return Arrays.copyOf(res, i);
    // }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= c; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : connections) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Traverse each station in increasing order, so
        // all groups are sorted in increasing order
        int[] groups = new int[c + 1];
        int groupId = 0;
        for (int station = 1; station <= c; station++) {
            if (groups[station] > 0) continue;
            groupId++;
            dfs(station, groupId, graph, groups);
        }

        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i <= groupId; i++) g.add(new ArrayList<>());

        for (int station = 1; station <= c; station++) {
            g.get(groups[station]).add(station);
        }

        int numType1 = 0;
        for (int[] query : queries) {
            if (query[0] == 1) numType1++;
        }

        int[] ptrs = new int[groupId + 1];
        boolean[] off = new boolean[c + 1];
        int[] res = new int[numType1];
        int i = 0;
        for (int[] query : queries) {
            int type = query[0];
            int x = query[1];

            if (type == 2) {
                off[x] = true;
            } else {
                if (!off[x]) {
                    res[i++] = x;
                    continue;
                }

                groupId = groups[x];
                List<Integer> group = g.get(groupId);
                int ptr = ptrs[groupId];

                while (ptr < group.size() && off[group.get(ptr)]) {
                    ptr++;
                }

                ptrs[groupId] = ptr;

                res[i++] = ptr < group.size() ? group.get(ptr) : -1;
            }
        }

        return res;
    }

    private void dfs(int node, int root, List<List<Integer>> graph, int[] groups) {
        // Already visited
        if (groups[node] > 0) return;
        
        groups[node] = root;

        for (int adj : graph.get(node)) {
            dfs(adj, root, graph, groups);
        }
    }
}