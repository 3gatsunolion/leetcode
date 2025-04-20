class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        # rabbits who answered the same number can be grouped
        # together as the same color if the number of rabbits
        # is <= how many they say there are

        sameColorCount = defaultdict(int)
        for numSameColor in answers:
            sameColorCount[numSameColor] += 1

        # compare answers to the number of rabbits that reported
        # the same answer
        res = 0
        for numSameColor, numRabbits in sameColorCount.items():
            numGroups = ceil(numRabbits / (numSameColor + 1))
            res += numGroups*(numSameColor+1)
        
        return res