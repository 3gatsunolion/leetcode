class Solution:
    def areSimilar(self, mat: List[List[int]], k: int) -> bool:
        # m, n = len(mat), len(mat[0])
        # k %= n
        # if k == 0:
        #     return True
            
        # for i in range(m):
        #     for j in range(n):
        #         if i % 2 == 0:
        #             if mat[i][(j - k + n) % n] != mat[i][j]:
        #                 return False
        #         else:
        #             if mat[i][(j + k) % n] != mat[i][j]:
        #                 return False
        # return True

        m, n = len(mat), len(mat[0])
        k %= n
        if k == 0:
            return True
            
        for i in range(m):
            for j in range(n):
                # This works for both odd and even because
                # we're seeing if the row can be split into l
                # chunks such that l is a multiple of k and each
                # of those l chunks are the same, so right shifting
                # by k will work
                if mat[i][(j + k) % n] != mat[i][j]:
                    return False
        return True