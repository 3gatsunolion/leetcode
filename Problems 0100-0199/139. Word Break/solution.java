class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public TrieNode getRoot() {
        return root;
    }
}

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Set<String> words = new HashSet<String>(wordDict);

        // Trie trie = new Trie();
        // for (String word : words) {
        //     trie.addWord(word);
        // }

        // int n = s.length();
        // boolean[] dp = new boolean[n + 1];
        // dp[0] = true;

        // for (int i = 0; i < n; i++) {
        //     if (!dp[i]) continue;

        //     TrieNode node = trie.getRoot();
        //     for (int j = i; j < n; j++) {
        //         char c = s.charAt(j);
        //         int index = c - 'a';

        //         if (node.children[index] == null) {
        //             break;
        //         }

        //         node = node.children[index];

        //         if (node.isEndOfWord) {
        //             dp[j + 1] = true;
        //         }
        //     }
        // }

        // return dp[n];

        Set<String> words = new HashSet<String>(wordDict);

        int maxLen = 0;
        for (String word : words) {
            if (word.length() > maxLen) {
                maxLen = word.length();
            }
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int end = 0; end < n; end++) {
            dp[end + 1] = false;
            for (int start = Math.max(0, end - maxLen + 1); start <= end; start++) {
                if (dp[start] && words.contains(s.substring(start, end + 1))) {
                    dp[end + 1] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}