class Solution:
    def clearStars(self, s: str) -> str:
        # If multiple same smallest character to left
        # of *, remove closest one, as we want to keep
        # smaller characters to the left as possible
        if '*' not in s:
            return s

        pos = [[] for _ in range(26)]
        minHeap = []
        keep = [True] * len(s)

        for i, c in enumerate(s):
            if c == '*':
                charToDelete = minHeap[0]
                p = ord(charToDelete)-ord('a')
                removeIndex = pos[p].pop()
                keep[i] = False
                keep[removeIndex] = False
                if not pos[p]:
                    heappop(minHeap)
            else:
                p = ord(c)-ord('a')
                if not pos[p]:
                    heappush(minHeap, c)
                pos[p].append(i)
        
        res = [s[i] for i, shouldKeep in enumerate(keep) if shouldKeep]
        return "".join(res)

        # res = [c for c in s]
        # minHeap = []
        # for i, c in enumerate(s):
        #     if c == '*':
        #         _, removeIndex = heappop(minHeap)
        #         removeIndex = -removeIndex
        #         res[removeIndex] = '' # delete smallest non * to its left
        #         res[i] = '' # delete *
        #     else:
        #         heappush(minHeap, [c, -i])
        
        # return ''.join(res)