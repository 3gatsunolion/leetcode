class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList();

        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList());
        
        int[] indegrees = new int[numCourses];
        for (int[] prereq : prerequisites) {
            graph.get(prereq[1]).add(prereq[0]);
            indegrees[prereq[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegrees[course] == 0) {
                q.offer(course);
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;

            for (int adj : graph.get(node)) {
                indegrees[adj]--;

                if (indegrees[adj] == 0) {
                    q.offer(adj);
                }
            }
        }

        return count == numCourses;
    }

    // public boolean canFinish(int numCourses, int[][] prerequisites) {
    //     List<Integer>[] graph = new ArrayList[numCourses];

    //     // Arrays.setAll(graph, i -> new ArrayList());
    //     for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList();

    //     for (int[] prereq : prerequisites) {
    //         graph[prereq[1]].add(prereq[0]);
    //     }

    //     int[] visited = new int[numCourses];
    //     for (int course = 0; course < numCourses; course++) {
    //         if (hasCycle(course, graph, visited)) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }

    // private boolean hasCycle(int node, List<Integer>[] graph, int[] visited) {
    //     if (visited[node] == -1) {
    //         return true;
    //     }

    //     if (visited[node] == 1) {
    //         return false;
    //     }

    //     visited[node] = -1;

    //     for (int adj : graph[node]) {
    //         if (hasCycle(adj, graph, visited)) {
    //             return true;
    //         }
    //     }

    //     visited[node] = 1;
    //     return false;
    // }
}