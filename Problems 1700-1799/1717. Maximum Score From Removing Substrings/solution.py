class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:

        def getMaxPoints(s, a, b, x, y):
            c1 = c2 = 0
            total = 0
            s += 'c'
            for c in s:
                if c == a:
                    c1 += 1
                elif c == b:
                    if c1 == 0:
                        c2 += 1
                    else:
                        total += x
                        c1 -= 1
                else:
                    total += y * min(c1, c2)
                    c1 = c2 = 0

            return total
        
        if x > y:
            return getMaxPoints(s, "a", "b", x, y)
        else:
            return getMaxPoints(s, "b", "a", y, x)
        # def removePairs(s, targetPair):
        #     stack = []

        #     for c in s:
        #         if c == targetPair[1] and stack and stack[-1] == targetPair[0]:
        #             stack.pop()
        #         else:
        #             stack.append(c)
            
        #     return "".join(stack)
        
        # highPair = "ab" if x > y else "ba"
        # lowPair = "ba" if highPair == "ab" else "ab"

        # total = 0
        # update1 = removePairs(s, highPair)
        # total += ((len(s) - len(update1)) // 2) * max(x, y)

        # update2 = removePairs(update1, lowPair)
        # total += ((len(update1) - len(update2)) // 2) * min(x, y)

        # return total