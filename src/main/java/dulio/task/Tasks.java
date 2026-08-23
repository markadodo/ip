package dulio.task;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;

import dulio.storage.Storage;

public class Tasks {
    private ArrayList<Task> tasks;
    private Storage storage;

    public Tasks() {
        this(new Storage(Path.of("data", "dulio.txt")));
    }

    public Tasks(Storage storage) {
        this.storage = storage;
        tasks = storage.load();
    }

    public void store(Task task) throws IOException {
        tasks.add(task);
        storage.save(tasks);
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

    public Task mark(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsDone();
        storage.save(tasks);
        return tasks.get(idx);
    }

    public Task unmark(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsNotDone();
        storage.save(tasks);
        return tasks.get(idx);
    }

    public Task delete(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        Task deletedTask = tasks.remove(idx);
        storage.save(tasks);
        return deletedTask;
    }
}
