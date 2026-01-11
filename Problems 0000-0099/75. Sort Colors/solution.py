class Solution:
    def sortColors(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # Red: 0, White: 1, Blue: 2
        n = len(nums)
        red = 0
        white = 0
        blue = n - 1

        while white <= blue:
            if nums[white] == 2:
                nums[blue], nums[white] = nums[white], nums[blue]
                blue -= 1
            elif nums[white] == 0:
                nums[red], nums[white] = nums[white], nums[red]
                red += 1
                white += 1
            else:
                white += 1