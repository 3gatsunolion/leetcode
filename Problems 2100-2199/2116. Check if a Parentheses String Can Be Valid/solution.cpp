class Solution {
public:
    bool canBeValid(string s, string locked) {
        int n = s.length();
        if (n % 2) return false;
        stack<char> stack;
        
        // Make sure if ) doesn't exceed (
        int numOpen = 0;
        for (int i = 0; i < n; i++) {
            if (locked[i] == '0' || s[i] == '(') {
                numOpen++;
            } else {
                numOpen--;
            }

            if (numOpen < 0) return false;
        }

        int numClose = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (locked[i] == '0' || s[i] == ')') {
                numClose++;
            } else {
                numClose--;
            }

            if (numClose < 0) return false;
        }

        return true;
    }
};