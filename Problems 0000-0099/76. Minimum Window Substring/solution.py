class Solution:
    def minWindow(self, s: str, t: str) -> str:
        tfreq = {}
        for c in t:
            tfreq[c] = tfreq.get(c, 0) + 1
        
        numReq = len(tfreq.keys())
        numReqMet = 0
        sfreq = {}
        left = 0
        n = len(s)
        minLen = n + 1
        start = 0
        for right in range(n):
            sfreq[s[right]] = sfreq.get(s[right], 0) + 1
            if sfreq[s[right]] == tfreq.get(s[right], 0):
                numReqMet += 1

            while numReqMet == numReq:
                currLen = right - left + 1
                if currLen < minLen:
                    minLen = currLen
                    start = left
                sfreq[s[left]] -= 1
                if s[left] in tfreq and sfreq[s[left]] < tfreq[s[left]]:
                    numReqMet -= 1
                if sfreq[s[left]] == 0:
                    del sfreq[s[left]]
                left += 1
        
        if minLen == n + 1:
            return ""
        return s[start:start+minLen]