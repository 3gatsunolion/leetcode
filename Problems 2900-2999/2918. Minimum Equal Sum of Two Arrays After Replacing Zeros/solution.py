class Solution:
    def minSum(self, nums1: List[int], nums2: List[int]) -> int:
        # since we're replacing 0 with strictly positive integers
        # the new total must increase (unless no zeroes)
        total1 = 0
        zero1 = 0
        for num in nums1:
            total1 += num
            if num == 0:
                zero1 += 1
        total2 = 0
        zero2 = 0
        for num in nums2:
            total2 += num
            if num == 0:
                zero2 += 1

        # swap, so we just deal with 1 case when totals are different
        if total1 < total2:
            total1, total2 = total2, total1
            zero1, zero2 = zero2, zero1

        # 1. If array sums are equal and if num zeroes are same, then
        # we just add numzeroes to total. if one does not have zeroes
        # but the other does, then return -1. if both have different
        # number of zeroes but both have zeroes, then add
        if total1 == total2:
            if zero1 == zero2:
                return total1 + zero1
            elif zero1 == 0 or zero2 == 0:
                return -1
            else:
                return total1 + max(zero1, zero2)
        else:
            # total1 > total2
            # 2. If one array has greater sum, then ideally we place 1
            # in all zero slots in greater array, then fill zeroes in
            # lesser array with (total1+zero1-total2). edge case
            # where no zeroes in lesser array
            if zero2 == 0 or (total2 + zero2 > total1 and zero1 == 0):
                return -1
            else:
                return max(total1 + zero1, total2 + zero2)