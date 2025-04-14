class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        # Only one subarray can be removed, so
        # find largest non-decreasing prefix and suffix
        # Either we take prefix or suffix on their own
        # or 1) take prefix and find longest suffix
        # that can be tacked on to form non-decreasing
        # 2) same as 1) but suffix and prefix flipped
        n = len(arr)
        if n < 2:
            return 0
        
        suffix = n-1
        while suffix > 0 and arr[suffix-1] <= arr[suffix]:
            suffix -= 1
        # entirely non-decreasing
        if suffix == 0:
            return 0
        res = suffix # delete everything before

        # combine with non-decreasing subarrays starting at 0
        i, j = 0, suffix
        while i < suffix and (i == 0 or arr[i-1] <= arr[i]):
            while j < len(arr) and arr[i] > arr[j]:
                j += 1
            res = min(res, j - i - 1)
            i += 1
        return res