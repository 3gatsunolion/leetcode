class Solution:
    def hIndex(self, citations: List[int]) -> int:
        numPapers = len(citations)
        # Observation: h is capped at numPapers
        buckets = [0]*(numPapers + 1)

        for c in citations:
            buckets[min(c, numPapers)] += 1
        
        count = 0
        for h in range(numPapers, -1, -1):
            count += buckets[h]
            if count >= h:
                return h
        return 0