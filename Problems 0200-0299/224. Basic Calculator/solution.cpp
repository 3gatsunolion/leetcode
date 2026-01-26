class Solution {
    public:
        int calculate(string s) {
            stack<int> stack;
            // Running sum at current level/context: () -> is a new level/context
            int res = 0;
            int num = 0;
            int sign = 1;
            for (char c : s) {
                if (isdigit(c)) {
                    num = num*10 + (c-'0');
                } else if (c == '+' || c == '-') {
                    res += num*sign;
                    sign = c == '+' ? 1 : -1;
                    num = 0;
                } else if (c == '(') {
                    // start new context
                    stack.push(res);
                    stack.push(sign);
                    sign = 1;
                    res = 0;
                } else if (c == ')') {
                    res += num*sign;
                    num = 0;
                    res = stack.top()*res;
                    stack.pop();
                    res += stack.top();
                    stack.pop();
                }
            }
    
            if (num != 0) res += num * sign;
            return res;
        }
    };