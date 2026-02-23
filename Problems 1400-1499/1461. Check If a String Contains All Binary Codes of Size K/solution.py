class Solution:
    def hasAllCodes(self, s: str, k: int) -> bool:
        # need = 1 << k
        # n = len(s)
        # max_substrings = n - k + 1
        # if n < k or max_substrings < need:
        #     return False
        # return len(set([s[start: start + k] for start in range(0, max_substrings)])) == need

        need = 1 << k
        n = len(s)
        max_substrings = n - k + 1
        if n < k or max_substrings < need:
            return False
        
        seen = [False] * need
        curr_num = 0
        all_ones = need - 1 # So we don't go over (need - 1)
        count = 0
        for i, c in enumerate(s):
            curr_num = ((curr_num << 1) & all_ones) | (c == '1')
            if not seen[curr_num]:
                seen[curr_num] = True
                count += 1
                if count == need:
                    return True

        return False