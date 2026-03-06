class Solution:
    def checkOnesSegment(self, s: str) -> bool:
        # seen = s[0] == '1'
        # for i in range(1, len(s)):
        #     if s[i] == '1':
        #         if seen and s[i - 1] != '1':
        #             return False
        #         seen = True
        
        # return True

        # Since there are NO leading zeros, s will ALWAYS start with 1
        # meaning if there is another segment of ones, then we will see
        # a "01", so that's all we need to check for
        return "01" not in s