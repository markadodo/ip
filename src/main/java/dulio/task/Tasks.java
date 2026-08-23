package dulio.task;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

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
        return formatTasks(tasks);
    }

    public String find(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ENGLISH);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<Integer> matchingNumbers = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase(Locale.ENGLISH).contains(normalizedKeyword)) {
                matchingTasks.add(tasks.get(i));
                matchingNumbers.add(i + 1);
            }
        }
        return formatTasks(matchingTasks, matchingNumbers);
    }

    private String formatTasks(ArrayList<Task> tasksToFormat) {
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        for (int i = 0; i < tasksToFormat.size(); i++) {
            taskNumbers.add(i + 1);
        }
        return formatTasks(tasksToFormat, taskNumbers);
    }

    private String formatTasks(ArrayList<Task> tasksToFormat, ArrayList<Integer> taskNumbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasksToFormat.size(); i++) {
            Task task = tasksToFormat.get(i);
            sb.append(" ").append(taskNumbers.get(i)).append(".[").append(task.getTypeIcon()).append("][")
                .append(task.getStatusIcon()).append("] ").append(task.toString()).append("\n");
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
