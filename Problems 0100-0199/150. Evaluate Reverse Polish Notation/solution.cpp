class Solution {
    public:
        int evalRPN(vector<string>& tokens) {
            unordered_map<string, function<int (int, int) >> map = {
                { "+" , [] (int a, int b) { return a + b; } },
                { "-" , [] (int a, int b) { return a - b; } },
                { "*" , [] (int a, int b) { return a * b; } },
                { "/" , [] (int a, int b) { return a / b; } }
            };
            std::stack<int> stack;
            for (string& s : tokens) {
                if (!map.count(s)) {
                    stack.push(stoi(s));
                } else {
                    int op2 = stack.top();
                    stack.pop();
                    int op1 = stack.top();
                    stack.pop();
                    stack.push(map[s](op1, op2));
                }
            }
            return stack.top();
        }
    
        bool isOperator(string& token) {
            return token == "+" || token == "-" || token == "*" || token == "/";
        }
    };