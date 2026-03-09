class Solution {
    public int minimumArea(int[][] grid) {
        int[] tl = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] br = new int[2];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;
                tl[0] = Math.min(tl[0], i);
                tl[1] = Math.min(tl[1], j);
                br[0] = Math.max(br[0], i);
                br[1] = Math.max(br[1], j);
            }
        }

        int w = br[1] - tl[1] + 1;
        int h = br[0] - tl[0] + 1;
        return w * h;
    }
}