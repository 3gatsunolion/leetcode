class Solution:
    def lexicalOrder(self, n: int) -> List[int]:
        # n = 250
        # 1, 2, 3, 4, 5, 6, 7, 8, 9
        # 10, 11,..., 19 | 20, 21, ..., 29 | ...
        # 100,...,109 | 110,...119 |
        # Tree traversal, preorder
        # Recursive is still technically O(1) space complexity
        # since height of tree is log10(n) -> (10^x=n) and n is max 50 000
        # which evaluates to around 5
        def dfs(curr):
            if curr > n:
                return
            res.append(curr)
            for i in range(10):
                cand = curr*10+i
                if cand > n:
                    return
                dfs(cand)
        
        res = []
        for i in range(1, 10):
            dfs(i)
        return res