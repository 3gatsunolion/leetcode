class Solution:
    def isValid(self, s: str) -> bool:
        # observe: since we're dealing with different types of
        # parentheses, whenever we encounter an opening paren,
        # that closing paren must come before the closing paren
        # of any other opening paren that came before the current
        # one
        stack = []
        for ch in s:
            if ch == '(':
                stack.append(')')
            elif ch == '{':
                stack.append('}')
            elif ch == '[':
                stack.append(']')
            # closing paren, must match MOST RECENTLY
            # added closing paren
            else:
                if len(stack) == 0 or stack.pop() != ch:
                    return False
        
        return len(stack) == 0