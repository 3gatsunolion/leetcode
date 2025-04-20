class Solution:
    def slidingPuzzle(self, board: List[List[int]]) -> int:
        target = "123450"
        initial = "".join(["".join([str(num) for num in row]) for row in board])
        if initial == target:
            return 0

        seen = set()
        seen.add(initial)

        m, n = 2, 3
        zeroPos = initial.index("0")

        goTo = [
            [1, 3], [0, 2, 4], [1, 5], [0, 4], [1, 3, 5], [2, 4]
        ]
        q = deque([[zeroPos, initial]])
        moves = 0
        while q:
            qLen = len(q)
            for _ in range(qLen):
                pos, puzzle = q.popleft()
                
                for i in goTo[pos]:
                    newPuzzle = list(puzzle)
                    newPuzzle[i], newPuzzle[pos] = newPuzzle[pos], newPuzzle[i]
                    newPuzzle = "".join(newPuzzle)
                    
                    if newPuzzle in seen:
                        continue
                    if newPuzzle == target:
                        return moves + 1
                    
                    seen.add(newPuzzle)

                    q.append([i, newPuzzle])
                    
            
            moves += 1
        
        return -1