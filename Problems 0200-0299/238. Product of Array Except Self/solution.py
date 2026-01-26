class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        # 0 will cause all numbers except itself to be 0
        # if only one zero, the one zero will have non-zero number
        # which is the product of all other numbers
        # if there is more than one zero, then the entire array will be
        # 0
        n = len(nums)
        res = [1]*n
        zero = -1
        numZeroes = 0
        for i in range(n):
            if nums[i] == 0:
                zero = i
                numZeroes += 1
            elif i > 0:
                res[i] = res[i-1]*nums[i-1]
        
        if numZeroes > 1:
            return [0]*n
        elif numZeroes == 1:
            product = 1
            for num in nums:
                if num != 0:
                    product *= num
            
            res = [0]*n
            res[zero] = product
            return res
        else:
            right = 1
            for i in range(n-1, -1, -1):
                res[i] *= right
                right *= nums[i]
            return res