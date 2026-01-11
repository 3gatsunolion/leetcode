class Solution {
    public:
        string simplifyPath(string path) {
            stack<string> stack;
            int n = path.size();
            for (int i = 0; i < n; i++) {
                if (path[i] == '/') continue;
    
                string name;
                while (i < n && path[i] != '/') {
                    name += path[i++];
                }
    
                if (name == "..") {
                    if (!stack.empty()) stack.pop();
                } else if (name != ".") {
                    stack.push(name);
                }
            }
    
            string res;
            while (!stack.empty()) {
                res = "/" + stack.top() + res;
                stack.pop();
            }
    
            if (res.length() == 0) return "/";
            return res;
        }
    };