class TrieNode {
    private TrieNode[] children;
    private String word;

    public TrieNode() {
        children = new TrieNode[26];
    }

    public void addWord(String word) {
        addWord(word, 0);
    }

    private void addWord(String word, int index) {
        if (index == word.length()) {
            this.word = word;
            return;
        }
        char ch = word.charAt(index);
        if (children[ch - 'a'] == null) {
            children[ch - 'a'] = new TrieNode();
        }
        TrieNode node = children[ch - 'a'];
        node.addWord(word, index + 1);
    }

    public TrieNode getChildNode(char ch) {
        return children[ch - 'a'];
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void remove(char ch) {
        children[ch - 'a'] = null;
    }

    public boolean isEmpty() {
        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode trie = new TrieNode();
        for (String word : words) {
            trie.addWord(word);
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(board, i, j, trie, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int x, int y, TrieNode trie, List<String> res) {
        if (trie.getWord() != null) {
            res.add(trie.getWord());
            trie.setWord(null);
        }

        if (x < 0 || y < 0 || x == board.length || y == board[x].length || board[x][y] == '#' || trie.getChildNode(board[x][y]) == null) return;

        TrieNode node = trie.getChildNode(board[x][y]);
        char tmp = board[x][y];
        board[x][y] = '#';

        dfs(board, x + 1, y, node, res);
        dfs(board, x - 1, y, node, res);
        dfs(board, x, y + 1, node, res);
        dfs(board, x, y - 1, node, res);

        board[x][y] = tmp;

        // Optimization: remove leave nodes that are not needed anymore
        if (node.isEmpty()) {
            trie.remove(board[x][y]);
        }
    }
}