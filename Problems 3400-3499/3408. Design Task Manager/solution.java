class Task implements Comparable<Task> {
    int userId;
    int taskId;
    int priority;

    Task(int userId, int taskId, int priority) {
        this.userId = userId;
        this.taskId = taskId;
        this.priority = priority;
    }

    public int compareTo(Task other) {
        if (this.priority == other.priority) {
            return other.taskId - this.taskId;
        }
        return other.priority - this.priority;
    }
}

// [userId: 2, taskId: 102, priority: 9]
// [userId: 1, taskId: 101, priority: 8]
// [userId: 4, taskId: 104, priority: 5]
// [userId: 3, taskId: 103, priority: 5]



class TaskManager {
    private PriorityQueue<Task> tasksToExecute;
    private Map<Integer, Task> tasksTracker;

    public TaskManager(List<List<Integer>> tasks) {
        tasksToExecute = new PriorityQueue();
        tasksTracker = new HashMap<>();

        for (List<Integer> task : tasks) {
            Task t = new Task(task.get(0), task.get(1), task.get(2));
            tasksToExecute.offer(t);
            tasksTracker.put(t.taskId, t);
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        tasksToExecute.offer(task);
        tasksTracker.put(taskId, task);
    }
    
    public void edit(int taskId, int newPriority) {
        Task task = tasksTracker.get(taskId);
        add(task.userId, taskId, newPriority);
    }
    
    public void rmv(int taskId) {
        tasksTracker.remove(taskId);
    }
    
    public int execTop() {
        while (!tasksToExecute.isEmpty()) {
            Task task = tasksToExecute.poll();

            if (tasksTracker.containsKey(task.taskId)) {
                Task compare = tasksTracker.get(task.taskId);
                if (compare.priority == task.priority && compare.userId == task.userId) {
                    // Remove from system once you execute
                    tasksTracker.remove(task.taskId);
                    return task.userId;
                }
            }
        }

        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */