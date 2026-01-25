class RandomizedSet {
    private List<Integer> nums;
    private Map<Integer, Integer> mp;

    public RandomizedSet() {
        nums = new ArrayList<>();
        mp = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if (mp.containsKey(val)) return false;
        mp.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!mp.containsKey(val)) return false;
        
        // We want O(1) time, so remove and replace with last element
        int i = mp.get(val);
        int lastEl = nums.get(nums.size() - 1);
        nums.set(i, lastEl);
        nums.remove(nums.size() - 1);
        mp.put(lastEl, i);
        mp.remove(val);
        return true;
    }
    
    public int getRandom() {
        int n = nums.size();
        int i = (int) (Math.random() * n);
        return nums.get(i);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */