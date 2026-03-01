class Solution:
    # def minOperations(self, s: str, k: int) -> int:
    #     n = len(s)
    #     if n < k:
    #         return -1

    #     zeros = 0
    #     for c in s:
    #         if c == '0':
    #             zeros += 1

    #     # All ones
    #     if zeros == 0:
    #         return 0

    #     # From line 51, it's explained that all number of zeros
    #     # at each step is:
    #     # zero_count = curr_zero_count + k - 2 * f, where f is number of zeros flipped
    #     # This means: 2 * f is always even, so we can rewrite:
    #     # zero_count % 2 = (curr_zero_count + k) % 2
    #     # So parity change on zero_count depends only on k
    #     # If k % 2 == 0, we can rewrite to:
    #     # zero_count % 2 = curr_zero_count % 2
    #     # -> This means zero_count never changes from its original parity
    #     # and since we want zero_count to get to 0, if original zero count
    #     # is odd, then we can never reach 0
    #     # If k % 2 == 1, then:
    #     # zero_count % 2 = (curr_zero_count + 1) % 2
    #     # This means zero_count switches parity each operation, so it is
    #     # possible to reach 0
    #     if (zeros % 2) == 1 and k % 2 == 0:
    #         return -1
        
    #     rem = zeros % k
    #     if rem == 0:
    #         return zeros // k

    #     def get_next(x):
    #         if next_zero[x] != x:
    #             next_zero[x] = get_next(next_zero[x])
    #         return next_zero[x]

    #     next_zero = [i for i in range(n + 1)]
    #     seen = [False] * (n + 1)
    #     seen[zeros] = True
    #     q = deque([(zeros, 0)]) # zeroes_left, steps_so_far

    #     while q:
    #         zeros, ops = q.popleft()

    #         # In one step, we can select at most min(k, zeros) zeros to flip
    #         # and we can select at most len(s) - zeros ones (that's how
    #         # many ones are currently in s)
    #         # -> Let f = number of zeros to flip
    #         # -> 0 <= f <= min(k, zeros)
    #         # -> k - f <= len(s) - zeros -> k - len(s) + zeros <= f
    #         lo = max(0, k - n + zeros)
    #         hi = min(k, zeros)
            
    #         # We flip f zeroes, and we flip k - f ones
    #         # k - f ones will turn to zero, so we must account for that
    #         # zeros - f + k - f = zeroes + k - 2 * f
    #         # Note: zero_count will increment by 2 each time f increments by 1
    #         # -> zeros + k - 2*f
    #         # -> zeros + k - 2*(f+1)
    #         # -> zeros + k - 2*(f+2)
    #         # Therefore, each operation changes zero count by an even number
    #         zero_count_lo = zeros + k - 2 * hi
    #         zero_count_hi = zeros + k - 2 * lo

    #         # Instead of scanning from new_zeros_lo to new_zeros_hi
    #         # with step 2, we can use path compression from union find
    #         # so we can skip over the intervals that have already been processed
    #         # and keep time complexity to O(n), since each zero count
    #         # is enqueued at most once.
    #         curr_zero_count = get_next(zero_count_lo)
    #         while curr_zero_count <= zero_count_hi:
    #             if curr_zero_count == 0:
    #                 return ops + 1

    #             if not seen[curr_zero_count]:
    #                 seen[curr_zero_count] = True
    #                 q.append((curr_zero_count, ops + 1))

    #             if curr_zero_count + 2 < len(next_zero):
    #                 next_zero[curr_zero_count] = get_next(curr_zero_count + 2)
    #                 curr_zero_count = next_zero[curr_zero_count]
    #             else:
    #                 curr_zero_count += 2
        
    #     return -1

    def minOperations(self, s: str, k: int) -> int:
        zero = s.count('0')
        n = len(s)

        if zero == 0: return 0

        if k == 1: return zero

        if n == k: return 1 if zero == n else -1

        if zero % k == 0: return zero // k

        if zero % 2 == 1 and k % 2 == 0: return -1

        # Each step, there's n - k unflipped slots
        unflipped_per_step = n - k

        ones = n - zero

        # Every 0 must be flipped an odd number of times.
        # Every 1 must be flipped an even number of times.
        # Let: m = # operations
        # Each operation we need to flip k bits, so we flip
        # m * k bits -> may be flipping same bit more than once
        # If the total number of "flip slots" (m * k) and
        # "ignore slots" (m * unflipped_per_step) satisfy the
        # parity requirements and the total capacity constraints,
        # there exists a way to assign those flips to indices.

        # 1. If m is odd:
        # 0's need to be flipped at least once and unflipped an even
        # number of times
        # 1's need to be unflipped odd number of times (flipped even # times)
        # -> we want to minimize m, so the cheapest way is:
        # Every '0' is flipped m times (Odd) -> (Needs 0 ignores)
        # Every '1' is flipped m - 1 times (Even) (Needs 1 ignore)
        # To make this work, you need enough total "ignore capacity" (m * unflipped_per_step) to give every single '1' at least one ignore slot
        # m * unflipped_per_step >= ones
        # -> m >= ones / unflipped_per_step
        odd = max(math.ceil(zero / k), math.ceil(ones / unflipped_per_step))
        # Adjust to make sure odd is odd
        odd += (odd % 2 == 0)

        # 2. If m is even:
        # 0's need to be flipped at least once and also not flipped
        # odd number of times
        # 1's need to be not flipped even number of times
        # To minimize m:
        # - flip 0s m - 1 times (ignore once)
        # - flip 1s m times (never ignore)
        # So we need: m * unflipped_per_step >= zero
        even = max(math.ceil(zero / k), math.ceil(zero / unflipped_per_step))
        # Adjust to make sure it's even
        even += even & 1

        # Note on math.ceil(zero / k): m * k must be large enough to flip
        # every '0' at least once:
        # m * k >= zero -> m >= zero / k
        # m * unflipped_per_step must be large enough to ignore every '1'
        # (if m is odd) or every '0' (if m is even)

        res = float('inf')

        # Each time we perform an operation:
        # Let's say f is the number of zeros we flip. Then:
        # new_zero = zero - f + k - f = zero + k - 2 * f
        # -> difference between number of zeros we have left between
        # each operation is k - 2 * f
        # -> this means k - 2 * f always has the same parity as k itself
        # -> if k is odd, the number of zeros changes by an odd amount each step
        # -> if k is even, the number of zeros changes by an even amount each step
        # 1. If m is odd:
        # -> a) if k is odd, then zero changes m * k = odd total amount
        # so for number of zeros to be zero at the end, we need initial
        # zero to be odd as well
        # -> b) if k is even, then zero changes m * k = even total amount
        # so for number of zeros to be zero at the end, we need initial
        # zero to be even as well
        # So zero and k need to have same parity if m is odd
        if (k & 1) == (zero & 1):
            res = min(res, odd)

        # 2. If m is even:
        # -> a) If k is odd, then zero changes m * k = even total amount
        # so zero has to be even if we want to reduce it to 0 at the end
        # -> b) If k is even, then same as above
        # So if m is even, then zero has to be even as well
        if zero % 2 == 0:
            res = min(res, even)

        return -1 if res == float('inf') else res