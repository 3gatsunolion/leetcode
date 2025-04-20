class MyCalendarTwo:

    def __init__(self):
        self.bookings = SortedDict(int)

    def book(self, startTime: int, endTime: int) -> bool:
        # line sweep
        if startTime not in self.bookings:
            self.bookings[startTime] = 0
        if endTime not in self.bookings:
            self.bookings[endTime] = 0
        self.bookings[startTime] += 1
        self.bookings[endTime] -= 1
        count = 0
        for delta in self.bookings.values():
            count += delta
            if count > 2:
                self.bookings[startTime] -= 1
                self.bookings[endTime] += 1
                if self.bookings[startTime] == 0:
                    del self.bookings[startTime]
                if self.bookings[endTime] == 0:
                    del self.bookings[endTime]
                return False
        return True


# Your MyCalendarTwo object will be instantiated and called as such:
# obj = MyCalendarTwo()
# param_1 = obj.book(startTime,endTime)