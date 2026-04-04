class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        # For number to be even, it must end in 0, 2, 4, 6, or 8
        freq = [0]*10
        for digit in digits:
            freq[digit] += 1
        res = []
        for i in range(1, 10):
            if freq[i] == 0:
                continue
            
            for j in range(10):
                minJ = 1
                if i == j: minJ += 1
                if freq[j] < minJ:
                    continue
                
                for k in range(0, 10, 2):
                    minK = 1
                    if k == i: minK += 1
                    if k == j: minK += 1
                    if freq[k] < minK:
                        continue
                    
                    num = i*100 + j*10 + k
                    res.append(num)

        return res