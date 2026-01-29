class Solution:
    def getSum(self, a: int, b: int) -> int:
        # In Python, integers have arbitrary precision, they can grow as
        # large as your RAM allows
        # If you have a negative number in python, it basically means
        # an infinite string of leading 1s, so carry << 1 for addition
        # could keep pushing that 1 further and further left forever,
        # and we'll get an infinite loop
 
        # Subtraction
        # 6 - 7 = -1 -> 2's complement is just 11111111
        # 110
        # 111
        # the carry will go like this 010 -> 100 -> 1000 to infinity for python
        
        # If larger number - smaller number -> carry will be cancelled
        # out eventually, since the larger number will have a 1 further
        # to the left
        # 13 - 10 = 3
        # 1101 
        # 1010

        # 0111
        # 0100

        # 0011
        # 0000

        # To keep it simple, we always want |a| >= |b|
        x, y = abs(a), abs(b)
        if x < y:
            return self.getSum(b, a)

        # |a| >= |b|, so if a > 0, regardless of b's sign, the sum will
        # be positive
        sign = 1 if a > 0 else -1

        if ((a > 0) ^ (b > 0)):
            # Subtract since they are different sign
            while y != 0:
                borrow = ~x & y
                x ^= y
                y = borrow << 1
        else:
            while y != 0:
                carry = x & y
                x ^= y
                y = carry << 1
        
        return x * sign