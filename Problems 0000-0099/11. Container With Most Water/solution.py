class Solution:
    def maxArea(self, height: List[int]) -> int:
        left = 0
        right = len(height) - 1

        res = 0
        while left < right:
            area = (right - left) * min(height[left], height[right])
            res = max(res, area)
            # always move if shorter, shorter height option
            # can be discarded since we're looking for maxarea.
            # since we're decreasing width, only way for bigger area
            # is for the next height to be higher.
            # height[left] == height[right] does not matter
            # bc if height[left] == height[right], the area for
            # (left+1,right) or (left,right-1) will be smaller 
            if height[left] <= height[right]:
                left += 1
            else:
                right -= 1
        
        return res