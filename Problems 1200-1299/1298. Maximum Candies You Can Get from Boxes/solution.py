class Solution:
    def maxCandies(self, status: List[int], candies: List[int], keys: List[List[int]], containedBoxes: List[List[int]], initialBoxes: List[int]) -> int:
        n = len(status)
        have = [False] * n
        q = deque()
        
        for box in initialBoxes:
            # box is opened, add to queue
            if status[box] == 1:
                q.append(box)
            have[box] = True
        
        res = 0
        while q:
            box = q.popleft()
            res += candies[box]

            # Process any boxes you had keys for previously
            # but could not open because you didn't have the
            # box yet

            for key in keys[box]:
                # Can only open box if you have it
                # Check status to make sure we're not
                # processing same box twice (only process
                # unopened boxes)
                if have[key] and status[key] == 0:
                    q.append(key)
                # Set it as "opened", even if we haven't
                # found box yet, if we find it contained
                # in a box later on, it'll be added to the queue
                status[key] = 1
            
            for contained in containedBoxes[box]:
                if status[contained] == 1:
                    q.append(contained)
                have[contained] = True
        
        return res