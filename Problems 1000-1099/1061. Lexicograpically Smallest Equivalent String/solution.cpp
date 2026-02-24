class UnionFind {
    private:
        vector<int> parent;
    public:
        UnionFind(int n): parent(n) {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
    
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    
        void unionByLex(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
    
            if (rootX == rootY) return;
    
            if (rootX < rootY) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
            }
        }
    };
    
    class Solution {
    public:
        string smallestEquivalentString(string s1, string s2, string baseStr) {
            int n = s1.length();
            UnionFind uf(26);
            for (int i = 0; i < n; i++) {
                uf.unionByLex(s1[i]-'a', s2[i]-'a');
            }
    
            string res;
            for (char c : baseStr) {
                int i = uf.find(c-'a');
                res += i+'a';
            }
            return res;
        }
    };