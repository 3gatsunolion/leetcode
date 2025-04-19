class BIT:
    def __init__(self, n):
        self.maxGaps = [0]*(n+1)
    
    def query(self, i):
        res = 0
        i += 1
        while i > 0:
            res = max(self.maxGaps[i], res)
            i -= i & -i
        return res
    
    def update(self, i, x):
        n = len(self.maxGaps)
        i += 1
        while i < n:
            self.maxGaps[i] = max(self.maxGaps[i], x)
            i += i & -i

class Solution:
    def getResults(self, queries: List[List[int]]) -> List[bool]:
        obstacles = SortedList()
        n = max(q[1] for q in queries) + 1
        obstacles.add(0)
        obstacles.add(n)
        for query in queries:
            if query[0] == 1:
                obstacles.add(query[1])
        
        bit = BIT(n)
        # Add all obstacles and remove one by one starting
        # from the back, since adding an obstacle will only
        # decrease the gaps, while removing will only increase
        # gaps
        for start, end in pairwise(obstacles):
            bit.update(end, end-start)
        
        res = []
        for query in reversed(queries):
            if query[0] == 1:
                # Remove
                x = query[1]
                # No duplicate obstacle, so this will
                # always be index to x
                i = obstacles.index(x)
                l = obstacles[i-1]
                r = obstacles[i+1]
                obstacles.remove(x)
                bit.update(r, r-l)
            else:
                x, sz = query[1], query[2]
                # unless x is an obstacle, we need to manually
                # find distance between x and closest
                # obstacle to the left
                i = obstacles.bisect_right(x)
                l = obstacles[i-1]
                res.append(bit.query(x) >= sz or (x-l) >= sz)
        
        res.reverse()
        return res