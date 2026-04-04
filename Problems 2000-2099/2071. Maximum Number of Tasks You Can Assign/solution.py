class Solution:
    def maxTaskAssign(self, tasks: List[int], workers: List[int], pills: int, strength: int) -> int:
        # Best case: min(len(tasks), len(workers)) tasks completed
        # Give tasks with smaller strength requirement to workers with least
        # strength but still >=, so we can make sure we leave the strongest
        # workers for the hardest tasks
        def canCompleteKTasks(k):
            # take k strongest worker -> if they can't complete
            # k weakest tasks the other weaker ones will not be able to
            # so we assign each k worker in order
            numWorkers = len(workers)
            if numWorkers < k:
                return False
            
            numTasks = len(tasks)
            q = deque()
            pillsLeft = pills
            t = 0
            for w in range(numWorkers-k, numWorkers):
                worker = workers[w]
                while t < k and worker + strength >= tasks[t]:
                    q.append(tasks[t])
                    t += 1

                # worker cannot complete any task
                if not q:
                    return False

                # Worker can complete "weakest" task without pill
                if q[0] <= worker:
                    q.popleft()
                else:
                    # Assign worker to highest task it can take on
                    # with help of pill (if available)
                    # Note: all tasks in q are sorted and are completable
                    # by current worker with help of pill
                    if pillsLeft > 0:
                        pillsLeft -= 1
                        q.pop()
                    else:
                        return False

            return True


        tasks = sorted(tasks)
        workers = sorted(workers)

        lo = 0
        hi = min(len(tasks), len(workers))

        while lo < hi:
            mid = lo + (hi - lo + 1) // 2
            if canCompleteKTasks(mid):
                lo = mid
            else:
                hi = mid - 1
        
        return lo