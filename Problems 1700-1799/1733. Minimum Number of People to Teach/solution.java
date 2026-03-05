class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        // 1. Find friends who cannot communicate with each other
        // 2. Find most popular language among these users
        // 3. Count users that can't speak that language
        int m = languages.length;
        boolean[][] canSpeak = new boolean[m][n];
        for (int i = 0; i < languages.length; i++) {
            for (int language : languages[i]) {
                canSpeak[i][language - 1] = true;
            }
        }

        Set<Integer> cannotCommunicate = new HashSet<>();
        for (int[] friendship : friendships) {
            int u = friendship[0] - 1;
            int v = friendship[1] - 1;

            if (languages[u].length > languages[v].length) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            boolean canCommunicate = false;
            for (int language : languages[u]) {
                if (canSpeak[v][language - 1]) {
                    canCommunicate = true;
                    break;
                }
            }

            if (!canCommunicate) {
                cannotCommunicate.add(u);
                cannotCommunicate.add(v);
            }

        }

        int[] languageFreq = new int[n];
        for (int user : cannotCommunicate) {
            for (int language : languages[user]) {
                languageFreq[language - 1]++;
            }
        }

        // int maxCount = 0;
        // for (int count : languageFreq) {
        //     if (count > maxCount) {
        //         maxCount = count;
        //     }
        // }

        // return cannotCommunicate.size() - maxCount;

        return cannotCommunicate.size() - Arrays.stream(languageFreq).max().getAsInt();
    }
}