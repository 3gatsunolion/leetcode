class TrieNode {
    private TrieNode[] children;
    private boolean isEndOfWord;
    private int id;
    public TrieNode() {
        children = new TrieNode[26];
    }

    public TrieNode[] children() {
        return children;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean isEnd) {
        isEndOfWord = isEnd;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Trie {
    private TrieNode root;
    private int numWords;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode root() {
        return root;
    }

    public int size() {
        return numWords;
    }

    public int insertWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int charId = word.charAt(i) - 'a';

            if (curr.children()[charId] == null) {
                curr.children()[charId] = new TrieNode();
            }
            curr = curr.children()[charId];
        }

        if (!curr.isEndOfWord()) {
            curr.setEndOfWord(true);
            curr.setId(numWords++);
            return curr.id();
        }
        return curr.id();
    }

    public int getId(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int charId = word.charAt(i) - 'a';

            if (curr.children()[charId] == null) return -1;
            curr = curr.children()[charId];
        }

        return curr.id();
    }
}

class Solution {
    // 1. Floyd Warshall version
    // Let V = number of unique strings in original and changed
    // X = sum of all string length in original and changed
    // N = source.length
    // Time complexity: O(X + V^3 + N^2)
    // Space: O(X + V^2 + N)
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Trie trie = new Trie();
        for (String s : original) trie.insertWord(s);
        for (String s : changed) trie.insertWord(s);

        int numWords = trie.size();
        int[][] minCost = new int[numWords][numWords];
        for (int i = 0; i < numWords; i++) {
            Arrays.fill(minCost[i], Integer.MAX_VALUE);
            minCost[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int u = trie.getId(original[i]);
            int v = trie.getId(changed[i]);

            if (minCost[u][v] > cost[i]) {
                minCost[u][v] = cost[i];
            }
        }

        for (int k = 0; k < numWords; k++) {
            for (int i = 0; i < numWords; i++) {
                if (minCost[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 0; j < numWords; j++) {
                    if (minCost[k][j] == Integer.MAX_VALUE) continue;
                    minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                }
            }
        }

        char[] sourceChars = source.toCharArray();
        char[] targetChars = target.toCharArray();
        int n = sourceChars.length;

        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i - 1] == Long.MAX_VALUE) continue;
            if (sourceChars[i - 1] == targetChars[i - 1] &&
                dp[i - 1] < dp[i]) {
                dp[i] = dp[i - 1];
            }
            TrieNode node1 = trie.root();
            TrieNode node2 = trie.root();
            for (int j = i; j <= n; j++) {
                int o = sourceChars[j - 1] - 'a';
                int t = targetChars[j - 1] - 'a';

                node1 = node1.children()[o];
                node2 = node2.children()[t];
                if (node1 == null || node2 == null) break;
                if (node1.isEndOfWord() &&
                    node2.isEndOfWord() &&
                    minCost[node1.id()][node2.id()] != Integer.MAX_VALUE &&
                    dp[j] > minCost[node1.id()][node2.id()] + dp[i - 1]) {
                        dp[j] = minCost[node1.id()][node2.id()] + dp[i - 1];
                }
            }
        }

        return dp[n] == Long.MAX_VALUE ? -1 : dp[n];
    }

    // // 2. Dijkstra version
    // // Let V = number of unique strings in original and changed
    // // E = number of edges (original.length)
    // // X = sum of all string length in original and changed
    // // N = source.length
    // // Time complexity: O(X + V*Elog(V) + N^2)
    // // Space: O(X + V^2 + E + N)
    // public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
    //     Map<Integer, List<int[]>> graph = new HashMap<>();
    //     Trie trie = new Trie();
    //     for (int i = 0; i < original.length; i++) {
    //         int u = trie.insertWord(original[i]);
    //         int v = trie.insertWord(changed[i]);
            
    //         if (!graph.containsKey(u)) {
    //             graph.put(u, new ArrayList<>());
    //         }
    //         graph.get(u).add(new int[] {v, cost[i]});
    //     }

    //     int numWords = trie.size();
    //     int[][] minCost = new int[numWords][numWords];
    //     for (int i = 0; i < numWords; i++) {
    //         Arrays.fill(minCost[i], Integer.MAX_VALUE);
    //         minCost[i][i] = 0;
    //     }

    //     for (int u : graph.keySet()) {
    //         Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    //         q.offer(new int[] {0, u});

    //         while (!q.isEmpty()) {
    //             int[] curr = q.poll();
    //             int currCost = curr[0];
    //             int v = curr[1];

    //             if (minCost[u][v] > currCost) continue;
    //             if (!graph.containsKey(v)) continue;
    //             for (int[] adj : graph.get(v)) {
    //                 int next = adj[0];
    //                 int newCost = currCost + adj[1];

    //                 if (minCost[u][next] > newCost) {
    //                     minCost[u][next] = newCost;
    //                     q.offer(new int[] {newCost, next});
    //                 }
    //             }
    //         }
    //     }

    //     char[] sourceChars = source.toCharArray();
    //     char[] targetChars = target.toCharArray();
    //     int n = sourceChars.length;

    //     long[] dp = new long[n + 1];
    //     Arrays.fill(dp, Long.MAX_VALUE);
    //     dp[0] = 0;
    //     for (int i = 1; i <= n; i++) {
    //         if (dp[i - 1] == Long.MAX_VALUE) continue;
    //         if (sourceChars[i - 1] == targetChars[i - 1] &&
    //             dp[i - 1] < dp[i]) {
    //             dp[i] = dp[i - 1];
    //         }
    //         TrieNode node1 = trie.root();
    //         TrieNode node2 = trie.root();
    //         for (int j = i; j <= n; j++) {
    //             int o = sourceChars[j - 1] - 'a';
    //             int t = targetChars[j - 1] - 'a';

    //             node1 = node1.children()[o];
    //             node2 = node2.children()[t];
    //             if (node1 == null || node2 == null) break;
    //             if (node1.isEndOfWord() &&
    //                 node2.isEndOfWord() &&
    //                 minCost[node1.id()][node2.id()] != Integer.MAX_VALUE &&
    //                 dp[j] > minCost[node1.id()][node2.id()] + dp[i - 1]) {
    //                     dp[j] = minCost[node1.id()][node2.id()] + dp[i - 1];
    //             }
    //         }
    //     }

    //     return dp[n] == Long.MAX_VALUE ? -1 : dp[n];
    // }
}