class TrieNode {

    private TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }

    public void addWord(String word) {
        addWord(word, 0);
    }

    private void addWord(String word, int i) {
        if (i == word.length()) {
            isEndOfWord = true;
            return;
        }

        char ch = word.charAt(i);
        if (children[ch - 'a'] == null) {
            children[ch - 'a'] = new TrieNode();
        }
        TrieNode node = children[ch - 'a'];
        node.addWord(word, i + 1);
    }

    public boolean search(String word) {
        return search(word, 0);
    }

    private boolean search(String word, int index) {
        if (index == word.length()) {
            return isEndOfWord;
        }

        char ch = word.charAt(index);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                TrieNode node = children[i];
                if (node != null && node.search(word, index + 1)) return true;
            }
            return false;
        }
        TrieNode node = children[ch - 'a'];
        return node != null && node.search(word, index + 1);
    }
}

class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        root.addWord(word);
    }
    
    public boolean search(String word) {
        return root.search(word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */