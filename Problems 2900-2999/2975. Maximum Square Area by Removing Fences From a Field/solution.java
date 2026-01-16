class Solution {
    private final int MOD = (int) Math.pow(10, 9) + 7;
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int numY = hFences.length + 2;
        int numX = vFences.length + 2;
        
        int[] yCoords = new int[numY];
        int[] xCoords = new int[numX];

        yCoords[0] = 1; yCoords[numY - 1] = m;
        xCoords[0] = 1; xCoords[numX - 1] = n;

        for (int i = 0; i < hFences.length; i++) {
            yCoords[i + 1] = hFences[i];
        }

        for (int i = 0; i < vFences.length; i++) {
            xCoords[i + 1] = vFences[i];
        }

        // Get all possible horizontal gaps we can get from the vertical bars
        // Optimization (?): Sort, so instead of Math.abs(xCoords[i] - xCoords[j]);
        // we can just do xCoords[i] - xCoords[j]
        Arrays.sort(xCoords);
        Arrays.sort(yCoords);

        Set<Long> xDiffs = new HashSet<>();
        for (int i = 0; i < numX; i++) {
            for (int j = 0; j < i; j++) {
                // long diff = Math.abs(xCoords[i] - xCoords[j]);
                long diff = xCoords[i] - xCoords[j];
                xDiffs.add(diff);
            }
        }

        long res = 0;
        for (int i = 0; i < numY; i++) {
            for (int j = 0; j < i; j++) {
                // long yDiff = Math.abs(yCoords[i] - yCoords[j]);
                long yDiff = yCoords[i] - yCoords[j];
                long cand = yDiff * yDiff;
                if (cand <= res) continue;

                if (xDiffs.contains(yDiff) && cand > res) {
                    res = cand;
                }
            }
        }

        if (res == 0) return -1;

        return (int) (res % MOD);
    }
}