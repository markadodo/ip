import java.util.ArrayList;

public class Tasks {
    private ArrayList<Task> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void store(String description) {
        store(new Task(description));
    }

    public void store(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public String list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int id = i + 1;
            Task t = tasks.get(i);
            sb.append(" ").append(id).append(".[").append(t.getTypeIcon()).append("][")
                .append(t.getStatusIcon()).append("] ").append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    public Task mark(int taskNumber) {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsDone();
        return tasks.get(idx);
    }

    public Task unmark(int taskNumber) {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsNotDone();
        return tasks.get(idx);
    }

    public Task delete(int taskNumber) {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        return tasks.remove(idx);
    }
}
