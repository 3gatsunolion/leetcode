from collections import deque
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        return self.letterCombinationsBFS(digits)
    #     if len(digits) == 0:
    #         return []
    #     numToLetters = {
    #         "2": "abc",
    #         "3": "def",
    #         "4": "ghi",
    #         "5": "jkl",
    #         "6": "mno",
    #         "7": "pqrs",
    #         "8": "tuv",
    #         "9": "wxyz",
    #     }
    #     res = []
    #     self.backtrack(digits, 0, "", numToLetters, res)
    #     return res

    # def backtrack(self, s, i, currPath, numToLetters, res):
    #     if i >= len(s):
    #         res.append(currPath)
    #         return
    #     for letter in numToLetters[s[i]]:
    #         self.backtrack(s, i + 1, currPath + letter, numToLetters, res)

    def letterCombinationsBFS(self, digits):
        if len(digits) == 0:
            return []

        numToLetters = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz",
        }
        
        q = deque([""])
        i = 0
        res = []
        while q:
            n = len(q)
            for _ in range(n):
                path = q.popleft()
                if len(path) == len(digits):
                    res.append(path)
                    continue
                for letter in numToLetters[digits[i]]:
                    q.append(path + letter)
            i += 1
    
        return res

