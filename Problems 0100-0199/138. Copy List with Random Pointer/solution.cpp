/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/

class Solution {
    public:
        Node* copyRandomList(Node* head) {
            if (!head) return nullptr;
    
            unordered_map<Node*, Node*> mp;
    
            // Copy all nodes
            Node* curr = head;
            while (curr) {
                Node* copy = new Node(curr->val);
                mp[curr] = copy;
                curr = curr->next;
            }
    
            // Set next and random
            curr = head;
            while (curr) {
                Node* copy = mp[curr];
                
                if (curr->next) {
                    copy->next = mp[curr->next];
                }
    
                if (curr->random) {
                    copy->random = mp[curr->random];
                }
    
                curr = curr->next;
            }
    
            return mp[head];
        }
    };