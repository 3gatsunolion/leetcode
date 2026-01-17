class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;
        for (int a : apple) {
            totalApples += a;
        }

        if (totalApples == 0) return 0;

        Arrays.sort(capacity);
        int m = capacity.length;

        int res = 0;
        for (int i = m - 1; i >= 0; i--) {
            res++;
            totalApples -= capacity[i];

            if (totalApples <= 0) break;
        }

        return res;
    }
}