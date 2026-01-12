class Solution:
    def candy(self, ratings: List[int]) -> int:
        numChildren = len(ratings)
        candies = [1] * numChildren # every child gets 1 candy
        for i in range(1, numChildren):
            if ratings[i] > ratings[i-1]:
                candies[i] = candies[i-1] + 1
        
        for i in range(numChildren-2, -1, -1):
            if ratings[i] > ratings[i+1]:
                candies[i] = max(candies[i], candies[i+1] + 1)

        return sum(candies)