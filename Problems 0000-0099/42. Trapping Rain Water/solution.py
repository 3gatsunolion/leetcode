class Solution:
    def trap(self, height: List[int]) -> int:
        # Two left right points starting at the ends
        left = 0
        right = len(height) - 1
        maxLeft = maxRight = 0

        totalWater = 0
        while left < right:
            # Note: height[left] or height[right] always points to maxLeft
            # or maxRight so far
            # if height[left] < height[right] this means height[right] is
            # maxRight
            # if height[left] >= height[right], height[left] is maxLeft
            # this is because we only increment or decrement left and right
            # if it's the smaller of the two
            # Therefore, if height[left] < height[right] and there's nothing
            # in between, then the total water trapped is maxLeft*width
            # and maxLeft is < height[right] (maxRight) so this will not
            # overflow
            if height[left] < height[right]:
                if height[left] > maxLeft:
                    maxLeft = height[left]
                else:
                    totalWater += maxLeft - height[left]
                left += 1
            # here, height[left] >= height[right], meaning height[left]
            # is maxLeft and maxLeft >= maxRight, therefore we cannot
            # calculate water from left to right, but from right to left
            else:
                if height[right] > maxRight:
                    maxRight = height[right]
                else:
                    totalWater += maxRight - height[right]
                right -= 1
        return totalWater