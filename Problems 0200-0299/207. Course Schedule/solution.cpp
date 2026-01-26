class Solution {
    public:
        bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
            vector<vector<int>> graph(numCourses);
            for (vector<int>& prereq : prerequisites) {
                int a = prereq[0];
                int b = prereq[1];
    
                graph[b].push_back(a);
            }
    
            vector<int> visited(numCourses, 0);
            for (int course = 0; course < numCourses; course++) {
                if (hasCycle(course, graph, visited)) return false;
            }
            return true;
        }
    
        bool hasCycle(int course, vector<vector<int>>& graph, vector<int>& visited) {
            if (visited[course] == -1) return true;
            if (visited[course] == 1) return false;
            visited[course] = -1;
            for (int nextCourse : graph[course]) {
                if (hasCycle(nextCourse, graph, visited)) return true;
            }
            visited[course] = 1;
            return false;
        }
    };