class Solution {
    public int minCost(String colors, int[] neededTime) {
        // // colors are all lowercase alphabet, so A is not one
        // char currColor = 'A';
        // int currMinTimeToRemove = 0;
        // int time = 0;
        // for (int i = 0; i < colors.length(); i++) {
        //     char color = colors.charAt(i);
        //     if (color == currColor) {
        //         // Consecutive colors, so we need to remove
        //         // one. If current balloon takes <= time,
        //         // we want to remove this one
        //         if (neededTime[i] <= currMinTimeToRemove) {
        //             time += neededTime[i];
        //         } else {
        //             time += currMinTimeToRemove;
        //             currMinTimeToRemove = neededTime[i];
        //         }
        //     } else {
        //         currColor = color;
        //         currMinTimeToRemove = neededTime[i];
        //     }
        // }
        // return time;

        char[] balloonColors = colors.toCharArray();
        int res = 0;

        for(int i = 1; i < balloonColors.length; i++) {
            if(balloonColors[i] == balloonColors[i - 1]) {
                // Remove every consecutive balloon except
                // balloon that takes max time
                int maxTime = neededTime[i - 1];
                int totalTime = neededTime[i - 1];
                while (i < balloonColors.length && balloonColors[i] == balloonColors[i - 1]) {
                    totalTime += neededTime[i];
                    maxTime = Math.max(neededTime[i], maxTime);
                    i++;
                }
                res += totalTime - maxTime;
            }
        }
        return res;
    }
}