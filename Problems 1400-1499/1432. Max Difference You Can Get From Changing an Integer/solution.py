class Solution:
    def maxDiff(self, num: int) -> int:
        maxNum = str(num)
        minNum = str(num)

        for digit in maxNum:
            if digit != '9':
                maxNum = maxNum.replace(digit, '9')
                break
        
        if minNum[0] == '1':
            for digit in minNum:
                if digit != minNum[0] and digit != '0':
                    minNum = minNum.replace(digit, '0')
                    break
        else:
            minNum = minNum.replace(minNum[0], '1')
        
        return int(maxNum) - int(minNum)