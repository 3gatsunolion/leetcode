class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        # Sort by start time of events
        events.sort(key=lambda x: x[0])

        n = len(events)
        minHeap = []

        res = 0
        i = 0
        day = 1
        while i < n or minHeap:
            # No events docked up, so we fast forward day to
            # next earliest event
            if i < n and len(minHeap) == 0:
                day = events[i][0]
            
            # Get rid of events cannot attend
            while minHeap and minHeap[0] < day:
                heappop(minHeap)
            
            # Add events that start today
            while i < n and events[i][0] == day:
                # Push end date, we want to attend event that ends
                # earliest first, so we maximize chance to attend
                # more events
                heappush(minHeap, events[i][1])
                i += 1
            
            if minHeap:
                heappop(minHeap)
                res += 1
                day += 1
        
        return res