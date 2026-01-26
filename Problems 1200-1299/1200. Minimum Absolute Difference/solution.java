class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        int currMinDiff = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];

            if (diff == currMinDiff) {
                res.add(Arrays.asList(arr[i - 1], arr[i]));
            } else if (diff < currMinDiff) {
                currMinDiff = diff;
                res = new ArrayList<>();
                res.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return res;
    }
}