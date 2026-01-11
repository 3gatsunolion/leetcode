class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        List<List<String>> res = new ArrayList<>();
        for (String s : strs) {
            // String key = hash(s);
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!anagrams.containsKey(key)) {
                List<String> group = new ArrayList<>();
                anagrams.put(key, group);
                res.add(group);
            }
            anagrams.get(key).add(s);
        }

        // return new ArrayList<>(anagrams.values());
        return res;
    }

    private String hash(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(freq[i] + (char) i + 'a');
        }
        return sb.toString();
    }
}