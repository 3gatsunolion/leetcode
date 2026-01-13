class Solution:
    def maxDistance(self, s: str, k: int) -> int:
        latitude, longitude = 0, 0
        res = 0
        
        for i in range(len(s)):
            if s[i] == 'N':
                longitude += 1
            elif s[i] == 'S':
                longitude -= 1
            elif s[i] == 'W':
                latitude += 1
            else:
                latitude -= 1
            res = max(res, min(abs(longitude) + abs(latitude) + 2 * k, i + 1))

        return res

        # res = 0
        # for diag in ["NW", "NE", "SW", "SE"]:
        #     dist = 0
        #     kLeft = k
        #     for c in s:
        #         if c in diag or kLeft > 0:
        #             dist += 1
        #             kLeft -= c not in diag
        #         else:
        #             dist -= 1
        #         res = max(res, dist)
        # return res