public class Tasks {
    private Task[] tasks;
    private int index = 0;

    public Tasks(int size) {
        // Use the provided size (max 100 recommended by requirement)
        tasks = new Task[size];
    }

    public void store(String description) {
        Task newTask = new Task(description);
        if (index >= tasks.length) {
            // ignore additional tasks beyond capacity
            return;
        }
        tasks[index++] = newTask;

    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            int id = i + 1;
            Task t = tasks[i];
            sb.append(" ").append(id).append(".[").append(t.getStatusIcon()).append("] ").append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    public Task mark(int taskNumber) {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= index) {
            return null;
        }
        tasks[idx].markAsDone();
        return tasks[idx];
    }

    public Task unmark(int taskNumber) {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= index) {
            return null;
        }
        tasks[idx].markAsNotDone();
        return tasks[idx];
    }
}
