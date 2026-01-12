/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/

class Solution {
    public:
        Node* cloneGraph(Node* node) {
            if (node == NULL) return NULL;
            unordered_map<Node*, Node*> clones;
    
            return dfs(node, clones);
        }
    
        Node* dfs(Node* node, unordered_map<Node*, Node*>& clones) {
            if (clones.find(node) != clones.end()) return clones[node];
    
            Node* clone = new Node(node->val);
            clones[node] = clone;
    
            for (Node* adj : node->neighbors) {
                clone->neighbors.push_back(dfs(adj, clones));
            }
    
            return clone;
        }
    };