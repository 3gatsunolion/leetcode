class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        stack = []
        for asteroid in asteroids:
            if asteroid >= 0 or len(stack) == 0:
                stack.append(asteroid)
            else:
                prev = stack.pop()
                size = abs(asteroid)
                if prev < 0:
                    # we won't hit same direction
                    stack.append(prev)
                    stack.append(asteroid)
                elif prev < size:
                    # pop asteroids that explode because
                    # curr is bigger
                    while stack and stack[-1] >= 0 and stack[-1] < size:
                        stack.pop()
                    if stack and stack[-1] == size:
                        # both explode
                        stack.pop()
                    # only if asteroid beats every asteroid in stack
                    # does it survive or same direction asteroid
                    elif len(stack) == 0 or stack[-1] < 0:
                        stack.append(asteroid)

                elif prev > size:
                    # beats current asteroid
                    stack.append(prev)
                
        return stack