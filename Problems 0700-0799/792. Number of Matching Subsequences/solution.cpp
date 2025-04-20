struct WordNode {
    const string& word;
    int index;
    WordNode(const string& word, int index) : word(word), index(index) {}
};

class Solution {
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        vector<vector<WordNode>> buckets(26);
        for (const string& word : words) {
            buckets[word[0]-'a'].emplace_back(word, 0);
        }

        int count = 0;
        for (char c : s) {
            vector<WordNode> bucket = buckets[c-'a'];
            buckets[c-'a'].clear();
            for (WordNode& node : bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    count++;
                } else {
                    buckets[node.word[node.index]-'a'].push_back(node);
                }
            }
        }
        return count;
    }
};