class Solution:
    def getLongestSubsequence(self, words: List[str], groups: List[int]) -> List[str]:
        # Start with first group, it doesn't matter if it's 0 or 1
        # it will always result in the longest alternating subsequence
        # because if there is a longer alternating subsequence later
        # that's starts different, that is not possible because we can
        # tack on the first group to create a larger one. So start from
        # first and take first alternating from there and so forth
        res = []
        prev = -1

        for i, group in enumerate(groups):
            if group != prev:
                res.append(words[i])
                prev = group

        return res