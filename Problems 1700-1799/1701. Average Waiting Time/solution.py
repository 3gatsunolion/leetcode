class Solution:
    def averageWaitingTime(self, customers: List[List[int]]) -> float:
        # already sorted by arrival time
        currTime = 0
        total = 0
        for arrival, time in customers:
            if currTime < arrival:
                currTime = arrival
            currTime += time
            total += currTime - arrival

        return total / len(customers)