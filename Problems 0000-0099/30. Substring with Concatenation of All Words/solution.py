class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        wordLen = len(words[0])
        numWords = len(words)
        wordFreq = defaultdict(int)
        for word in words:
            wordFreq[word] += 1
        
        n = len(s)
        res = []
        # Since each word is length wordLen, so a concatenation of them will
        # always be wordLen+wordLen+wordLen, so we can try different
        # starting points that will result in different words. Once starting
        # point goes past wordLen, then it's a starting point we've already
        # encountered
        for i in range(0, wordLen):
            seen = defaultdict(int)
            start = i
            wordsSeen = 0
            for wordStart in range(i, n-wordLen+1, wordLen):
                word = s[wordStart:wordStart+wordLen]
                if word in wordFreq:
                    seen[word] += 1
                    wordsSeen += 1

                    while seen[word] > wordFreq[word]:
                        wordToDelete = s[start:start+wordLen]
                        seen[wordToDelete] -= 1
                        wordsSeen -= 1
                        if seen[wordToDelete] == 0:
                            del seen[wordToDelete]
                        start += wordLen
                    
                    if wordsSeen == numWords:
                        res.append(start)
                else:
                    # Reset
                    seen = defaultdict(int)
                    wordsSeen = 0
                    start = wordStart + wordLen

        return res