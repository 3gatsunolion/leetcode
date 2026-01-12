class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        for token in tokens:
            if token.isnumeric() or (token[0] == "-" and len(token) > 1):
                num = int(token)
                stack.append(num)
            else:
                num2 = stack.pop()
                num1 = stack.pop()
                if token == "+":
                    stack.append(num1 + num2)
                elif token == "-":
                    stack.append(num1 - num2)
                elif token == '*':
                    stack.append(num1*num2)
                else:
                    stack.append(int(num1 / num2))
        
        return stack[0]