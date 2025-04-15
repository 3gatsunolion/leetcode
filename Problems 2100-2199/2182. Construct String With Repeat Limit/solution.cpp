class Solution {
public:
    string repeatLimitedString(string s, int repeatLimit) {
        vector<int> freq(26, 0);
        for (char c : s) {
            freq[c-'a']++;
        }

        string repeatLimitString = "";
        int i = 25;
        while (i >= 0) {
            if (freq[i] == 0) continue;

            while (freq[i]) {
                int repeat = min(repeatLimit, freq[i]);
                repeatLimitString += string(repeat, i+'a');
                freq[i] -= repeat;

                if (freq[i]) {
                    int nextCharIndex = i - 1;
                    while (nextCharIndex >= 0 && !freq[nextCharIndex]) {
                        nextCharIndex--;
                    }

                    if (nextCharIndex < 0) {
                        break;
                    } else {
                        freq[nextCharIndex]--;
                        repeatLimitString += nextCharIndex + 'a';
                    }
                }
            }
            i--;
        }
        return repeatLimitString;
    }
    string repeatLimitedString2(string s, int repeatLimit) {
        // lexographically largest, always start with highest
        // possible letter (i.e. 'z'), and use the most possible
        // of it in a row <= repeatLimit
        // if character has left over characters that can still
        // be used, use next lexographically large letter
        // (if exists). if exists, use one letter and then go
        // back to previous larger letter. repeat process
        // till no more of that letter
        vector<int> count(26, 0);
        for (char c : s) {
            count[c-'a']++;
        }

        priority_queue<pair<char, int>> maxHeap;

        for (int i = 0; i < count.size(); i++) {
            if (!count[i]) continue;
            maxHeap.push({ i + 'a', count[i] });
        }

        string repeatLimitString = "";
        while (!maxHeap.empty()) {
            auto [ch1, available] = maxHeap.top();
            maxHeap.pop();
            int repeat = min(available, repeatLimit);
            available -= repeat;
            repeatLimitString += string(repeat, ch1);
            if (available > 0 && !maxHeap.empty()) {
                auto [ch2, total] = maxHeap.top();
                maxHeap.pop();
                repeatLimitString += ch2;
                if (total > 1) {
                    maxHeap.push({ ch2, total - 1 });
                }
                maxHeap.push({ ch1, available });
            }
        }
        return repeatLimitString;
    }
};