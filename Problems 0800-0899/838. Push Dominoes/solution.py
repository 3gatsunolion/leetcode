class Solution:
    def pushDominoes(self, dominoes: str) -> str:
        # 1. if we see -> <-, then in odd cases, we'll have a standing dominoe
        # in middle
        # 2. if we see <- ->, these don't have effect on each other,
        # only if they make -> <- with another arrow in past/future

        # L at start is for case with first "push" domino. If first push
        # domino is L, then we'll have all L's from start to push domino
        # If first push domino is R, it'll all be stationary
        # R at the end is for case where no dominoes were pushed. We need
        # to fill with all '.'
        dominoes = 'L' + dominoes + 'R'
        # Index of last dominoe that was pushed
        lastPush = 0
        res = ""
        for i in range(1, len(dominoes)):
            if dominoes[i] == '.':
                continue
            
            if lastPush:
                res += dominoes[lastPush]
            numBetween = i - lastPush - 1
            # Same direction
            if dominoes[i] == dominoes[lastPush]:
                res += dominoes[i]*numBetween
            # All standing
            elif dominoes[lastPush] == 'L' and dominoes[i] == 'R':
                res += '.'*numBetween
            else:
                res += 'R'*(numBetween//2)
                res += '.'*(numBetween % 2)
                res += 'L'*(numBetween//2)
        
            lastPush = i
        return res