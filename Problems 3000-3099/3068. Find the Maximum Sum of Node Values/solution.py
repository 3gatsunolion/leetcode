class Solution:
    def maximumValueSum(self, nums: List[int], k: int, edges: List[List[int]]) -> int:
        # Observation: a^k^k = a
        # -> when you select a pair of nodes connected with each other,
        # you may have to select the same node more than once and perform
        # XOR operation more than once. XOR the same number odd number of times
        # will result in a^k. XOR the same number even number of times
        # will result in just a.
        # Since we can apply operation any amount of times, and since edges
        # represent a valid tree, all nodes have a path to one another,
        # meaning we can XOR any two nodes as a pair, where we just XOR
        # along the path, so edges is useless to think about. All nodes
        # in between two nodes will result as themselves because they are
        # XORed twice.
        # So we just consider number of nodes to XOR. We want to XOR
        # numbers that will increase original val. If number of nodes
        # to XOR is even, we don't have to do anything, since we just
        # XOR as pairs. If there is an odd one out, we want to XOR
        # with the number with smallest deficit when XORed. If none
        # exists (no other number left to XOR), then we choose number
        # will smallest increase when XORed to leave out.
        
        best_sum = 0
        count = 0
        sacrifice = float("inf")
        for num in nums:
            best_sum += max(num ^ k, num)
            count += (num ^ k) > num
            sacrifice = min(sacrifice, abs(num - (num ^ k)))
        
        return best_sum - (0 if count % 2 == 0 else sacrifice)