class Solution:
    def countPrimeSetBits(self, left: int, right: int) -> int:
        # The constraint 1 <= left <= right <= 10^6 tells you that the maximum 
        # number you can get is 10^6.

        # This number is represented as 1111 01000010 01000000 in binary, which 
        # means that the most amount of bits set (for this problem) is 19:
        # 111 11111111 11111111.
        # All the prime numbers up to 19 (inclusive) are [2, 3, 5, 7, 11, 13, 17, 19].
        primes = {2, 3, 5, 7, 11, 13, 17, 19}
        count = 0
        for num in range(left, right + 1):
            # bits = 0
            # while num != 0:
            #     bits += (num & 1)
            #     num >>= 1
            # if bits in primes:
            #     count += 1
            if num.bit_count() in primes:
                count += 1
        
        return count