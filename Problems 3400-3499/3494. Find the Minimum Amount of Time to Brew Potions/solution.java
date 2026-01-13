class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        // skillSum[i] = skill[0] + ... + skill[i - 1]
        long[] skillSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            skillSum[i + 1] = skillSum[i] + skill[i];
        }

        // Earliest start time for next round
        long start = 0;
        for (int j = 1; j < m; j++) {
            long currStart = 0;
            for (int i = 0; i < n; i++) {
                // start + skillSum[i + 1] * mana[j - 1]
                // = earliest time ith wizard can start
                // brewing current jth potion
                // So if we minus (skillSum[i] * mana[j] =
                // time it will take previous i wizards to
                // brew current jth potion) we get earliest
                // start time based on current wizard
                // We make sure we take max with previous
                // currStart results to make sure it's doable
                currStart = Math.max(currStart, start + skillSum[i + 1] * mana[j - 1] - skillSum[i] * mana[j]);
            }
            start = currStart;
        }
        return start + skillSum[n] * mana[m - 1];
    }
    // public long minTime(int[] skill, int[] mana) {
    //     int n = skill.length;
    //     // done[i] -> earliest wizard can finish brewing
    //     // potions
    //     long[] done = new long[n + 1];

    //     for (int manaCapacity : mana) {
    //         for (int i = 1; i <= n; i++) {
    //             // Synchronize the earliest time each
    //             // wizard can finish brewing the i - 1th potion
    //             // Since we're overriding previous results,
    //             // here done[i - 1] is the earliest previous
    //             // wizard can finish brewing i - 1th potion
    //             // and done[i] is earliest current wizard can
    //             // finish i - 2th potion (there is possibility
    //             // previous wizard can finish brewing this
    //             // potion before current wizard finishes brewing
    //             // previous potion)
    //             done[i] = Math.max(done[i], done[i - 1]) + skill[i - 1] * manaCapacity;
    //         }
    //         // Since previous iteration, we may have kept
    //         // pushing back the times, we need to reset
    //         // the end times to the correct ones
    //         for (int i = n - 1; i > 0; i--) {
    //             done[i] = done[i + 1] - skill[i] * manaCapacity;
    //         }
    //     }

    //     return done[n];
    // }
}