class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        stack = []
        res = [0] * n
        prevTime = 0

        for log in logs:
            fn, eventType, time = log.split(":")
            fn = int(fn)
            time = int(time)
            
            if eventType == 'start':
                if stack:
                    res[stack[-1]] += time - prevTime
                stack.append(fn)
                prevTime = time
            else:
                stack.pop()
                res[fn] += time - prevTime + 1
                prevTime = time + 1
        
        return res