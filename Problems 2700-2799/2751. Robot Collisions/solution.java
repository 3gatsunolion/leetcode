class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        // Robots start out at different positions
        // Moving at same speed, so LL or RR or LR will never meet
        // Parenthese approach?
        int n = positions.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++)
            indices[i] = i;

        Arrays.sort(indices, (a, b) -> positions[a] - positions[b]);

        int[] stack = new int[n];
        int top = -1;
        for (int i : indices) {
            int dir = directions.charAt(i);
            if (dir == 'L') {
                // We don't have to put this robot on the stack. Since it's
                // going L, no robot to the right of it will collide with it
                // So we know stack only contains right moving robots
                while (top >= 0 && healths[i] > 0) {
                    int rightRobotIdx = stack[top];
                    if (healths[rightRobotIdx] == healths[i]) {
                        // Cancel out
                        top--;
                        healths[rightRobotIdx] = healths[i] = 0;
                    } else if (healths[rightRobotIdx] > healths[i]) {
                        healths[rightRobotIdx]--;
                        healths[i] = 0;
                    } else {
                        top--;
                        healths[rightRobotIdx] = 0;
                        healths[i]--;
                    }
                }
            } else {
                stack[++top] = i;
            }
        }

        List<Integer> health = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                health.add(healths[i]);
            }
        }

        return health;
    }
}