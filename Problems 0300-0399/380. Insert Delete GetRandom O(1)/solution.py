import random
class RandomizedSet:

    def __init__(self):
        self.nums = []
        self.mp = {}

    def insert(self, val: int) -> bool:
        if val in self.mp:
            return False
        self.mp[val] = len(self.nums)
        self.nums.append(val)
        return True

    def remove(self, val: int) -> bool:
        # Replace num to remove with last number in array
        # since popping off takes O(1)
        if val not in self.mp:
            return False
        numToReplace = self.nums[-1]
        i = self.mp[val]
        self.nums[i] = numToReplace
        self.nums.pop()
        # This line before del line in case
        # of array only has one num
        self.mp[numToReplace] = i
        del self.mp[val]
        return True

    def getRandom(self) -> int:
        # i = random.randint(0, len(self.nums)-1)
        # return self.nums[i]
        return random.choice(self.nums)


# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()