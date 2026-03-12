class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        rank = new int[n];
    }

    public UnionFind(int[] parent, int[] rank) {
        this.parent = parent;
        this.rank = rank;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        if (rank[rootX] < rank[rootY]) {
            int tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }

        parent[rootY] = rootX;

        if (rank[rootX] == rank[rootY]) {
            rank[rootX]++;
        }

        return true;
    }

    public UnionFind clone() {
        return new UnionFind(parent.clone(), rank.clone());
    }
}

class Solution {
    public int maxStability(int n, int[][] edges, int k) {
        UnionFind uf = new UnionFind(n);

        int edgesUsed = 0;
        int stability = Integer.MAX_VALUE;

        List<int[]> upgradable = new ArrayList<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int m = edge[3];
            // Must be included
            if (m == 1) {
                // Cycle found
                if (!uf.union(u, v)) {
                    return -1;
                }
                edgesUsed++;
                stability = Math.min(stability, s);
            } else {
                upgradable.add(edge);
            }
        }

        if (edgesUsed == n - 1) return stability;

        // We want to maximize stability, so we want to choose the edges
        // with largest strength
        upgradable.sort((a, b) -> b[2] - a[2]);
        List<Integer> strengths = new ArrayList<>();
        for (int[] edge : upgradable) {
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int m = edge[3];
            if (uf.union(u, v)) {
                edgesUsed++;
                strengths.add(s);
            }
        }

        if (edgesUsed != n - 1) return -1;

        // REMEMBER: strengths is decreasing from largest to smallest
        int m = strengths.size();
        if (m != 0) {
            if (k == 0) {
                // Can't upgrade
                stability = Math.min(stability, strengths.get(m - 1));
            } else if (k >= m) {
                stability = Math.min(stability, strengths.get(m - 1) * 2);
            } else {
                // We x2 all smallest k strengths, so minimum strength
                // will be smallest strength * 2 and the smallest strength
                // we didn't upgrade
                int smallest = Math.min(strengths.get(m - 1) * 2, strengths.get(m - k - 1));
                stability = Math.min(stability, smallest);
            }
        }
        
        return stability;
    }

    // public int maxStability(int n, int[][] edges, int k) {
    //     UnionFind uf = new UnionFind(n);

    //     int edgesUsed = 0;
    //     int minMust = Integer.MAX_VALUE;
    //     int maxUpgradable = -1;
    //     int min = Integer.MAX_VALUE;

    //     List<int[]> upgradable = new ArrayList<>();

    //     for (int[] edge : edges) {
    //         int u = edge[0];
    //         int v = edge[1];
    //         int s = edge[2];
    //         int m = edge[3];

    //         min = Math.min(min, m);
    //         // Must be included
    //         if (m == 1) {
    //             // Cycle found
    //             if (!uf.union(u, v)) {
    //                 return -1;
    //             }
    //             edgesUsed++;
    //             minMust = Math.min(minMust, s);
    //         } else {
    //             upgradable.add(edge);
    //             maxUpgradable = Math.max(maxUpgradable, s);
    //         }
    //     }

    //     if (edgesUsed == n - 1) return minMust;

    //     if (upgradable.size() != 0) {
    //         int lo = min;
    //         int hi = upgradable.size() != edges.length ? minMust : (k == 0 ? maxUpgradable : 2 * maxUpgradable);
    //         int edgesLeft = n - 1 - edgesUsed;
    //         int stability = -1;
    //         while (lo <= hi) {
    //             int mid = lo + (hi - lo) / 2;

    //             if (check(upgradable, uf.clone(), mid, edgesLeft, k)) {
    //                 lo = mid + 1;
    //                 stability = mid;
    //             } else {
    //                 hi = mid - 1;
    //             }
    //         }

    //         return stability;
    //     }

    //     return -1;
    // }

    // private boolean check(List<int[]> edges, UnionFind uf, int minStrength, int edgesLeft, int k) {
    //     List<int[]> mustUpgrade = new ArrayList<>();
    //     for (int[] edge : edges) {
    //         int u = edge[0];
    //         int v = edge[1];
    //         int s = edge[2];
            
    //         if (s >= minStrength) {
    //             if (uf.union(u, v)) edgesLeft--;
    //         } else if ((s * 2) >= minStrength) {
    //             mustUpgrade.add(edge);
    //         }
    //     }

    //     if (edgesLeft == 0) return true;

    //     for (int[] edge : mustUpgrade) {
    //         int u = edge[0];
    //         int v = edge[1];
    //         if (!uf.union(u, v)) continue;
    //         // Cannot upgrade any more and we need to connect
    //         // u and v because they were dijointed
    //         if (k == 0) {
    //             return false;
    //         }
    //         k--;
    //         edgesLeft--;
    //     }

    //     return edgesLeft == 0;
    // }

    // public int maxStability(int n, int[][] edges, int k) {
    //     UnionFind uf = new UnionFind(n);

    //     int edgesUsed = 0;
    //     int minMust = Integer.MAX_VALUE;
    //     int maxUpgradable = -1;
    //     int min = Integer.MAX_VALUE;

    //     List<int[]> upgradable = new ArrayList<>();

    //     for (int[] edge : edges) {
    //         int u = edge[0];
    //         int v = edge[1];
    //         int s = edge[2];
    //         int m = edge[3];

    //         min = Math.min(min, m);
    //         // Must be included
    //         if (m == 1) {
    //             // Cycle found
    //             if (!uf.union(u, v)) {
    //                 return -1;
    //             }
    //             edgesUsed++;
    //             minMust = Math.min(minMust, s);
    //         } else {
    //             upgradable.add(edge);
    //             maxUpgradable = Math.max(maxUpgradable, s);
    //         }
    //     }

    //     if (edgesUsed == n - 1) return minMust;

    //     if (upgradable.size() != 0) {
    //         upgradable.sort((a, b) -> b[2] - a[2]);
    //         int lo = min;
    //         int hi = upgradable.size() != edges.length ? minMust : (k == 0 ? maxUpgradable : 2 * maxUpgradable);
    //         int edgesLeft = n - 1 - edgesUsed;
    //         int stability = -1;
    //         while (lo <= hi) {
    //             int mid = lo + (hi - lo) / 2;

    //             if (check(upgradable, uf.clone(), mid, edgesLeft, k)) {
    //                 lo = mid + 1;
    //                 stability = mid;
    //             } else {
    //                 hi = mid - 1;
    //             }
    //         }

    //         return stability;
    //     }

    //     return -1;
    // }

    // private boolean check(List<int[]> edges, UnionFind uf, int minStrength, int edgesLeft, int k) {
    //     for (int[] edge : edges) {
    //         int u = edge[0];
    //         int v = edge[1];
    //         int s = edge[2];

    //         // We know edges is in decreasing order, so is s * 2 < minStrength
    //         // then we know the rest of edge strength < minStrength
    //         if (s * 2 < minStrength) return false;

    //         if (!uf.union(u, v)) continue;

    //         // We have to upgrade
    //         if (s < minStrength) {
    //             // Have to upgrade but can't
    //             if (k == 0) {
    //                 return false;
    //             } else {
    //                 k--;
    //             }
    //         }
    //         edgesLeft--;

    //         if (edgesLeft == 0) return true;
    //     }

    //     return false;
    // }
}