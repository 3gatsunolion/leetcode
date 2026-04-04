class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;

        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder decode = new StringBuilder();
        for (int startCol = 0; startCol < cols; startCol++) {
            for (int r = 0; r < rows; r++) {
                int c = startCol + r;
                if (c >= cols) break;
                int i = cols * r + c;
                decode.append(encodedText.charAt(i));
            }
        }

        // return decode.toString().stripTrailing();

        // Remove trailing spaces
        int end = decode.length() - 1;
        while (end >= 0 && decode.charAt(end) == ' ') {
            end--;
        }

        return decode.substring(0, end + 1);
    }
}