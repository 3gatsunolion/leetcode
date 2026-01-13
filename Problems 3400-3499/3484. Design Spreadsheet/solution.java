class Spreadsheet {
    private Map<String, Integer> spreadsheet;

    public Spreadsheet(int rows) {
        spreadsheet = new HashMap<>();
    }
    
    public void setCell(String cell, int value) {
        spreadsheet.put(cell, value);
    }
    
    public void resetCell(String cell) {
        spreadsheet.remove(cell);
    }
    
    public int getValue(String formula) {
        String[] parts = formula.substring(1).split("\\+");
        int res = 0;
        for (String part : parts) {
            res += mapToValue(part);
        }
        return res;
    }

    private int mapToValue(String s) {
        return Character.isLetter(s.charAt(0)) ? spreadsheet.getOrDefault(s, 0) : Integer.parseInt(s);
    }
}

// class Spreadsheet {
//     private int[][] spreadsheet;

//     public Spreadsheet(int rows) {
//         spreadsheet = new int[rows][26];
//     }
    
//     public void setCell(String cell, int value) {
//         int[] coord = getCellCoord(cell);
//         spreadsheet[coord[0]][coord[1]] = value;
//     }
    
//     public void resetCell(String cell) {
//         int[] coord = getCellCoord(cell);
//         spreadsheet[coord[0]][coord[1]] = 0;
//     }
    
//     public int getValue(String formula) {
//         String[] parts = formula.substring(1).split("\\+");
//         int res = 0;
//         for (String part : parts) {
//             if (Character.isDigit(part.charAt(0))) {
//                 res += Integer.valueOf(part);
//             } else {
//                 int[] coord = getCellCoord(part);
//                 res += spreadsheet[coord[0]][coord[1]];
//             }
//         }
//         return res;
//     }

//     private int[] getCellCoord(String cell) {
//         int row = Integer.valueOf(cell.substring(1)) - 1;
//         int col = cell.charAt(0) - 'A';
//         return new int[] {row, col};
//     }
// }

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */