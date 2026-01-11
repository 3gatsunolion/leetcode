class Solution {
    // public List<List<Integer>> combinationSum(int[] candidates, int target) {
    //     // Optimization, since candidates > 0 and target > 0
    //     // Arrays.sort(candidates);
    //     List<List<Integer>> res = new ArrayList<>();
    //     findCombos(0, candidates, target, new ArrayList<>(), res);
    //     return res;
    // }

    // private void findCombos(int start, int[] candidates, int remain, List<Integer> combo, List<List<Integer>> res) {
    //     if (remain < 0) {
    //         return;
    //     }
    //     if (remain == 0) {
    //         res.add(new ArrayList<>(combo));
    //         return;
    //     }
        
    //     for (int i = start; i < candidates.length; i++) {
    //         combo.add(candidates[i]);
    //         findCombos(i, candidates, remain - candidates[i], combo, res);
    //         combo.remove(combo.size() - 1);
    //     }
    // }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Optimization, since candidates > 0 and target > 0
        // Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        findCombos(0, candidates, target, new ArrayList<>(), res);
        return res;
    }

    private void findCombos(int i, int[] candidates, int remain, List<Integer> combo, List<List<Integer>> res) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            res.add(new ArrayList<>(combo));
            return;
        }
        if (i == candidates.length) {
            return;
        }

        // pick
        combo.add(candidates[i]);
        findCombos(i, candidates, remain - candidates[i], combo, res);
        combo.remove(combo.size() - 1);

        // don't pick
        findCombos(i + 1, candidates, remain, combo, res);
    }
}