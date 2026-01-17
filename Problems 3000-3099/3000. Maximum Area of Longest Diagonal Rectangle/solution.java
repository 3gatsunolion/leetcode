class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int longest = 0;
        int res = 0;
        for (int[] dimension : dimensions) {
            int height = dimension[0];
            int width = dimension[1];
            int diagonal = width * width + height * height; // avoid sqrt
            if (diagonal > longest) {
                longest = diagonal;
                res = width * height;
            } else if (diagonal == longest) {
                res = Math.max(res, width * height);
            } 
        }
        return res;
    }
}