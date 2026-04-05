class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> robots[a] - robots[b]);
        Arrays.sort(walls);

        // wallsLeft[i] = number of walls <= robot_i can destroy
        int[] wallsLeft = new int[n];
        int[] wallsRight = new int[n];
        int[] wallsBetweenPrevRobot = new int[n];
        
        int m = walls.length;
        int left = 0;
        int right = 0;
        int wallIdx = 0;
        int firstWallAfterPrevRobot = 0;
        for (int i = 0; i < n; i++) {
            int robotIdx = indices[i];
            while (right < m && walls[right] <= robots[robotIdx]) {
                right++;
            }

            int firstWallRightOfRobot = right;

            // Move wallIdx to first wall >= robot
            while (wallIdx < m && walls[wallIdx] < robots[robotIdx]) {
                wallIdx++;
            }

            int firstWallAtOrRightOfRobot = wallIdx;

            // LEFT SIDE CALCULATION
            int leftLimit = robots[robotIdx] - distance[robotIdx];

            // Prevent overlap with previous robot
            if (i > 0) {
                int prev = indices[i - 1];
                leftLimit = Math.max(leftLimit, robots[prev] + 1);
            }

            while (left < m && walls[left] < leftLimit) {
                left++;
            }

            int firstValidLeftWall = left;
            wallsLeft[i] = firstWallRightOfRobot - firstValidLeftWall;

            // RIGHT SIDE CALCULATION
            int rightLimit = robots[robotIdx] + distance[robotIdx];

            if (i < n - 1) {
                int next = indices[i + 1];
                rightLimit = Math.min(rightLimit, robots[next] - 1);
            }

            while (right < m && walls[right] <= rightLimit) {
                right++;
            }

            int lastValidRightWall = right;
            wallsRight[i] = lastValidRightWall - firstWallAtOrRightOfRobot;

            if (i > 0) {
                int prev = indices[i - 1];
                while (firstWallAfterPrevRobot < m && walls[firstWallAfterPrevRobot] < robots[prev]) {
                    firstWallAfterPrevRobot++;
                }

                wallsBetweenPrevRobot[i] = firstWallRightOfRobot - firstWallAfterPrevRobot;
            }
        }
        
        // Max unique walls destroyed if current robot shoots left
        int maxShootLeft = wallsLeft[0];
        // Max unique walls destroyed if current robot shoots right
        int maxShootRight = wallsRight[0];
        for (int i = 1; i < n; i++) {
            int shootLeft = Math.max(
                // prev robot shoots left (no overlap)
                maxShootLeft + wallsLeft[i],
                // prev robot shoots right
                maxShootRight - wallsRight[i - 1]
                    + Math.min(wallsLeft[i] + wallsRight[i - 1],
                               wallsBetweenPrevRobot[i])
            );

            int shootRight = wallsRight[i] + Math.max(
                maxShootLeft,
                maxShootRight
            );

            maxShootLeft = shootLeft;
            maxShootRight = shootRight;
        }

        return Math.max(maxShootLeft, maxShootRight);
    }
}