class Solution {
    public boolean exist(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }

        // Optimization: check if possible with enough characters
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            freq.put(word.charAt(i), freq.getOrDefault(word.charAt(i), 0) + 1);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (freq.containsKey(board[i][j])) {
                    freq.put(board[i][j], freq.get(board[i][j]) - 1);
                }
            }
        }

        for (char ch : freq.keySet()) {
            if (freq.get(ch) > 0) {
                return false;
            }
        }

        // Time complexity: O(m * n * 4 ^ len(word))
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int row, int col) {
        if (i == word.length()) {
            return true;
        }

        if (row < 0 || col < 0 || row == board.length || col == board[row].length || board[row][col] != word.charAt(i)) {
            return false;
        }

        board[row][col] = '#';
        boolean exists = dfs(board, word, i + 1, row + 1, col) ||
                         dfs(board, word, i + 1, row - 1, col) ||
                         dfs(board, word, i + 1, row, col + 1) ||
                         dfs(board, word, i + 1, row, col - 1);
        board[row][col] = word.charAt(i);

        return exists;
    }
}