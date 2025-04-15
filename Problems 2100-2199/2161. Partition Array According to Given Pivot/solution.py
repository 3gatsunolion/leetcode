class Solution:
    def pivotArray(self, nums: List[int], pivot: int) -> List[int]:
        less = []
        same = []
        greater = []
        for num in nums:
            if num < pivot:
                less.append(num)
            elif num == pivot:
                same.append(num)
            else:
                greater.append(num)
        
        return less + same + greater