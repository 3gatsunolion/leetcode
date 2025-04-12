class Solution {
public:
    long long countOfSubstrings(string word, int k) {
        int n = word.length();
        int vowelTotal = 5;
        vector<int> vowelFreq(26, 0);

        vector<int> nextConsonant(n);
        int lastConsonant = n;
        for (int i = n-1; i >= 0; i--) {
            nextConsonant[i] = lastConsonant;
            if (!isVowel(word[i])) lastConsonant = i;
        }

        long long res = 0;
        int left = 0, vowelCount = 0, consonantCount = 0;
        for (int right = 0; right < n; right++) {
            if (isVowel(word[right])) {
                vowelFreq[word[right]-'a']++;
                vowelCount += vowelFreq[word[right]-'a'] == 1 ? 1 : 0;
            } else {
                consonantCount++;
            }

            while (consonantCount > k) {
                if (isVowel(word[left])) {
                    vowelFreq[word[left]-'a']--;
                    vowelCount -= vowelFreq[word[left]-'a'] == 0 ? 1 : 0;
                } else {
                    consonantCount--;
                }
                left++;
            }

            while (vowelCount == vowelTotal && consonantCount == k) {
                res += nextConsonant[right] - right;
                if (isVowel(word[left])) {
                    vowelFreq[word[left]-'a']--;
                    vowelCount -= vowelFreq[word[left]-'a'] == 0 ? 1 : 0;
                } else {
                    consonantCount--;
                }
                left++;
            }
        }

        return res;
    }

    bool isVowel(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
};