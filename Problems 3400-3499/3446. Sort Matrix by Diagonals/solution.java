class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];

        for (int blDiag = 0; blDiag < m; blDiag++) {
            int row = blDiag, col = 0;

            int[] diag = new int[m - blDiag];
            for (int i = 0; i < diag.length; i++) {
                diag[i] = grid[row++][col++];
            }

            Arrays.sort(diag);
            row = blDiag; col = 0;

            for (int i = diag.length - 1; i >= 0; i--) {
                res[row++][col++] = diag[i];
            }
        }

        for (int trDiag = 1; trDiag < n; trDiag++) {
            int[] diag = new int[n - trDiag];
            int row = 0, col = trDiag;
            for (int i = 0; i < diag.length; i++) {
                diag[i] = grid[row++][col++];
            }
            Arrays.sort(diag);
            row = 0; col = trDiag;
            for (int i = 0; i < diag.length; i++) {
                res[row++][col++] = diag[i];
            }
        }

        return res;
        // Map<Integer, List<Integer>> diagonals = new HashMap<>();
        // int m = grid.length, n = grid[0].length;

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         int key = i - j;
        //         if (!diagonals.containsKey(key)) {
        //             diagonals.put(key, new ArrayList());
        //         }
        //         diagonals.get(key).add(grid[i][j]);
        //     }
        // }

        // for (int key : diagonals.keySet()) {
        //     List<Integer> diagonal = diagonals.get(key);
        //     if (key < 0) {
        //         diagonal.sort((a, b) -> b - a);
        //     } else {
        //         diagonal.sort((a, b) -> a - b);
        //     }
        // }
        
        // int[][] res = new int[m][n];
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         int key = i - j;
        //         List<Integer> diagonal = diagonals.get(key);
        //         res[i][j] = diagonal.get(diagonal.size() - 1);
        //         diagonal.remove(diagonal.size() - 1);
        //     }
        // }
        // return res;
    }
}