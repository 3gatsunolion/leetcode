class Solution {
public:
    vector<string> letterCombinations(string digits) {
        if (digits.empty()) {
            return {};
        }
        string digitToLetter[] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        vector<string> res;
        computePhoneNumbers(0, digits, "", digitToLetter, res);
        return res;
    }

    void computePhoneNumbers(int i, string digits, string phoneNum, string digitToLetter[], vector<string>& output) {
        if (i == digits.length()) {
            output.push_back(phoneNum);
            return;
        }

        for (char c : digitToLetter[digits[i]-'0']) {
            computePhoneNumbers(i+1, digits, phoneNum + c, digitToLetter, output);
        }
    }    
};