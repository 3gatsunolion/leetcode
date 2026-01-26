class TrieNode {

    private TrieNode[] children;
    private boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }

    public void insert(String word) {
        insert(word, 0);
    }

    private void insert(String word, int i) {
        if (i == word.length()) {
            isEndOfWord = true;
            return;
        }

        char ch = word.charAt(i);
        if (children[ch - 'a'] == null) {
            children[ch - 'a'] = new TrieNode();
        }
        TrieNode node = children[ch - 'a'];
        node.insert(word, i + 1);
    }

    public boolean search(String word) {
        return search(word, 0);
    }

    private boolean search(String word, int i) {
        if (i == word.length()) {
            return isEndOfWord;
        }

        char ch = word.charAt(i);
        if (children[ch - 'a'] == null) return false;
        TrieNode node = children[ch - 'a'];
        return node.search(word, i + 1);
    }

    public boolean startsWith(String word) {
        return startsWith(word, 0);
    }

    private boolean startsWith(String word, int i) {
        if (i == word.length()) {
            return true;
        }

        char ch = word.charAt(i);
        if (children[ch - 'a'] == null) return false;
        TrieNode node = children[ch - 'a'];
        return node.startsWith(word, i + 1);
    }
}

class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        root.insert(word);
    }
    
    public boolean search(String word) {
        return root.search(word);
    }
    
    public boolean startsWith(String prefix) {
        return root.startsWith(prefix);
    }
}

// class Trie {

//     private TrieNode root;

//     public Trie() {
//         root = new TrieNode();
//     }
    
//     public void insert(String word) {
//         TrieNode node = root;
//         for (int i = 0; i < word.length(); i++) {
//             char ch = word.charAt(i);
//             if (node.children[ch - 'a'] == null) {
//                 node.children[ch - 'a'] = new TrieNode();
//             }
//             node = node.children[ch - 'a'];
//         }
//         node.isEndOfWord = true;
//     }
    
//     public boolean search(String word) {
//         TrieNode node = root;
//         for (int i = 0; i < word.length(); i++) {
//             char ch = word.charAt(i);
//             if (node.children[ch - 'a'] == null) {
//                 return false;
//             }
//             node = node.children[ch - 'a'];
//         }
//         return node.isEndOfWord;
//     }
    
//     public boolean startsWith(String prefix) {
//         TrieNode node = root;
//         for (int i = 0; i < prefix.length(); i++) {
//             char ch = prefix.charAt(i);
//             if (node.children[ch - 'a'] == null) {
//                 return false;
//             }
//             node = node.children[ch - 'a'];
//         }
//         return true;
//     }
// }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */