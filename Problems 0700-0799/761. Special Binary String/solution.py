class Solution:
    # Time complexity: O(n^2)
    # Worst case input: "111...000"
    # -> Recursion depth of n / 2
    # -> n + (n-2) + (n-4) + ... + 2
    # = O(n²)
    # -> No sorting, since each step you're sorting array of length 1
    # -> Worst case for sorting: "10101010"
    # -> No recursion depth
    def makeLargestSpecial(self, S):
        if len(S) < 3:
            return S
        
        # Special string:
        # At any point of the string, you cannot have more 0's than 1's.
        # Kind of like valid parentheses?
        # Must start with 1 and end in 0
        balance = 0
        start = 0
        consec = []
        for i, c in enumerate(S):
            if c == '1':
                balance += 1
            else:
                balance -= 1
            
            if balance == 0:
                inner = self.makeLargestSpecial(S[start + 1:i])
                consec.append("1" + inner + "0")
                start = i + 1
        
        consec.sort(reverse=True)
        return "".join(consec)