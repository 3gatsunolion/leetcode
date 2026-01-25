class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;

        char[] chars = s.toCharArray();

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int currRow = 0;
        int step = 1;
        for (char c : chars) {
            rows[currRow].append(c);

            if (currRow == 0) {
                step = 1;
            } else if (currRow == numRows - 1) {
                step = -1;
            }

            currRow += step;
        }

        for (int i = 1; i < numRows; i++) {
            rows[0].append(rows[i]);
        }

        return rows[0].toString();
    }
}