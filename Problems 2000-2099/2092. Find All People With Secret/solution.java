class Solution {

    class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
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

            if (rank[rootY] > rank[rootX]) {
                int tmp = rootX;
                rootX = rootY;
                rootY = tmp;
            }

            parent[rootY] = rootX;

            if (rank[rootY] == rank[rootX]) {
                rank[rootX]++;
            }
        }

        public void reset(int x) {
            parent[x] = x;
            rank[x] = 0;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        UnionFind uf = new UnionFind(n);

        uf.union(0, firstPerson);

        // Group by meeting times
        int maxTime = 0;
        for (int[] meet : meetings) {
            maxTime = Math.max(maxTime, meet[2]);
        }

        List<int[]>[] meetingGroups = new List[maxTime + 1];
        for (int[] meet : meetings) {
            int x = meet[0], y = meet[1], t = meet[2];

            if (meetingGroups[t] == null) {
                meetingGroups[t] = new ArrayList<>();
            }

            meetingGroups[t].add(new int[] {x, y});
        }

        for (int t = 1; t <= maxTime; t++) {
            if (meetingGroups[t] == null) continue;

            for (int[] meet : meetingGroups[t]) {
                uf.union(meet[0], meet[1]);
            }

            // Reset any people that did not get the secret during this time
            for (int[] meet : meetingGroups[t]) {
                if (!uf.isConnected(0, meet[0])) {
                    uf.reset(meet[0]);
                }

                if (!uf.isConnected(0, meet[1])) {
                    uf.reset(meet[1]);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.isConnected(0, i)) {
                res.add(i);
            }
        }
        return res;
    }

    // TLE
    // public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    //     Map<Integer, List<Long>> graph = new HashMap<>();
    //     for (int[] meet : meetings) {
    //         int x = meet[0], y = meet[1], t = meet[2];

    //         graph.computeIfAbsent(x, k -> new ArrayList<>())
    //             // since y < n, then (t * n + y) // n = t
    //              .add(t * (long) n + y);
            
    //         graph.computeIfAbsent(y, k -> new ArrayList<>())
    //              .add(t * (long) n + x);
    //     }

    //     // earliest time person i knows secret
    //     int[] earliest = new int[n];
    //     // time will never reach Integer.MAX_VALUE so it's safe
    //     Arrays.fill(earliest, Integer.MAX_VALUE);
    //     earliest[0] = 0;
    //     earliest[firstPerson] = 0;

    //     Queue<Long> q = new LinkedList<>();

    //     q.offer(0L);
    //     q.offer((long) firstPerson);

    //     while (!q.isEmpty()) {
    //         long curr = q.poll();
    //         int person = (int) (curr % n), time = (int) (curr / n);

    //         for (long adj : graph.getOrDefault(person, Collections.emptyList())) {
    //             int nextPerson = (int) (adj % n), meetTime = (int) (adj / n);

    //             if (meetTime >= time && earliest[nextPerson] > meetTime) {
    //                 earliest[nextPerson] = meetTime;
    //                 q.offer(1l * meetTime * n + nextPerson);
    //             }
    //         }
    //     }

    //     List<Integer> res = new ArrayList<>();
    //     for (int i = 0; i < n; i++) {
    //         if (earliest[i] != Integer.MAX_VALUE) {
    //             res.add(i);
    //         }
    //     }
    //     return res;
    // }

    // public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
    //     Map<Integer, List<Long>> graph = new HashMap<>();
    //     for (int[] meet : meetings) {
    //         int x = meet[0], y = meet[1], t = meet[2];

    //         graph.computeIfAbsent(x, k -> new ArrayList<>())
    //             // since y < n, then (t * n + y) // n = t
    //              .add(t * (long) n + y);
            
    //         graph.computeIfAbsent(y, k -> new ArrayList<>())
    //              .add(t * (long) n + x);
    //     }

    //     // earliest time person i knows secret
    //     boolean[] knowsSecret = new boolean[n];

    //     Queue<int[]> q = new PriorityQueue<>((a, b) -> {
    //         return a[0] - b[0];
    //     });

    //     q.offer(new int[] {0, 0});
    //     q.offer(new int[] {0, firstPerson});

    //     while (!q.isEmpty()) {
    //         int[] curr = q.poll();
    //         int person = curr[1], time = curr[0];
            
    //         // Skip stale
    //         if (knowsSecret[person]) continue;
    //         knowsSecret[person] = true;

    //         for (long adj : graph.getOrDefault(person, Collections.emptyList())) {
    //             int nextPerson = (int) (adj % n), meetTime = (int) (adj / n);

    //             if (!knowsSecret[nextPerson] && meetTime >= time) {
    //                 q.offer(new int[] {meetTime, nextPerson});
    //             }
    //         }
    //     }

    //     List<Integer> res = new ArrayList<>();
    //     for (int i = 0; i < n; i++) {
    //         if (knowsSecret[i]) {
    //             res.add(i);
    //         }
    //     }
    //     return res;
    // }
}