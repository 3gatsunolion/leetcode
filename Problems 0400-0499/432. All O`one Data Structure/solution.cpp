class Node {
    public:
        unordered_set<string> keys;
        int count;
        Node* prev;
        Node* next;
    
        Node(int count = 0): count(count), prev(NULL), next(NULL) {
    
        }
    
        void addKey(string& key) {
            keys.insert(key);
        }
    
        void removeKey(string& key) {
            keys.erase(key);
        }
    
        string getAnyKey() {
            if (keys.size() > 0) {
                // string key = keys.pop();
                // keys.insert(key);
                return *keys.begin();
            }
            return "";
        }
    };
    
    class DoublyLinkedList {
    public:
        Node* head = new Node();
        Node* tail = new Node();
        DoublyLinkedList() {
            head->next = tail;
            tail->prev = head;
        }
    
        void insertNodeBefore(Node* node, Node* prevNode) {
            prevNode->prev = node->prev;
            prevNode->next = node;
            node->prev->next = prevNode;
            node->prev = prevNode;
        }
    
        void insertNodeAfter(Node* node, Node* nextNode) {
            nextNode->next = node->next;
            nextNode->prev = node;
            node->next->prev = nextNode;
            node->next = nextNode;   
        }
    
        void removeNode(Node* node) {
            Node* prev = node->prev;
            Node* next = node->next;
            prev->next = next;
            next->prev = prev;
            node->prev = nullptr;
            node->next = nullptr;
        }
    
        void removeKeyFromNode(Node* node, string& key) {
            node->removeKey(key);
            if (node->keys.size() == 0) {
                removeNode(node);
            }
        }
    };
    
    class AllOne {
    private:
        unordered_map<string, Node*> mp;
        DoublyLinkedList dll;
    public:
        AllOne(): dll() {
        }
        
        void inc(string key) {
            if (mp.find(key) != mp.end()) {
                Node* node = mp[key];
                if (node->next == dll.tail || node->next->count != node->count + 1) {
                    dll.insertNodeAfter(node, new Node(node->count + 1));
                }
                Node* nextNode = node->next;
                nextNode->addKey(key);
                dll.removeKeyFromNode(node, key);
                mp[key] = nextNode;
            } else {
                // Check if 1 bucket/node exists
                if (dll.head->next == dll.tail || dll.head->next->count != 1) {
                    dll.insertNodeAfter(dll.head, new Node(1));
                }
                Node* oneCountNode = dll.head->next;
                oneCountNode->addKey(key);
                mp[key] = oneCountNode;
            }
    
        }
        
        void dec(string key) {
            Node* node = mp[key];
            if (node->count == 1) {
                // Remove
                dll.removeKeyFromNode(node, key);
                mp.erase(key);
            } else {
                int newCount = node->count - 1;
                if (node->prev->count != newCount) {
                    dll.insertNodeBefore(node, new Node(newCount));
                }
                Node* prevNode = node->prev;
                prevNode->addKey(key);
                mp[key] = prevNode;
                dll.removeKeyFromNode(node, key);
            }
        }
        
        string getMaxKey() {
            if (dll.tail->prev == dll.head) {
                return "";
            }
            return dll.tail->prev->getAnyKey();
        }
        
        string getMinKey() {
            if (dll.head->next == dll.tail) {
                return "";
            }
            return dll.head->next->getAnyKey();
        }
    };
    
    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne* obj = new AllOne();
     * obj->inc(key);
     * obj->dec(key);
     * string param_3 = obj->getMaxKey();
     * string param_4 = obj->getMinKey();
     */