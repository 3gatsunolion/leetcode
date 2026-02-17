class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int total = m * n;
        int[] res = new int[total];

        int row = 0, col = 0;
        for (int i = 0; i < total; i++) {
            res[i] = mat[row][col];
            // Moving diagonal up
            if ((row + col) % 2 == 0) {
                if (col == n - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else {
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }

        return res;

        // int m = mat.length;
        // int n = mat[0].length;

        // Map<Integer, List<Integer>> mp = new HashMap<>();

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         int key = i + j;
        //         if (!mp.containsKey(key)) {
        //             mp.put(key, new ArrayList());
        //         }
        //         mp.get(key).add(mat[i][j]);
        //     }
        // }

        // int[] res = new int[m * n];
        // int groupId = 0;
        // int i = 0;
        // while (mp.containsKey(groupId)) {
        //     if (groupId % 2 == 0) {
        //         Collections.reverse(mp.get(groupId));
        //     }
        //     for (int val : mp.get(groupId)) {
        //         res[i++] = val;
        //     }
        //     groupId++;
        // }
        // return res;
    }
}