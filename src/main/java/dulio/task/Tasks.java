package dulio.task;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;

import dulio.storage.Storage;

/** Manages the task collection and persists changes through Storage. */
public class Tasks {
    /** Tasks in their current display order. */
    private ArrayList<Task> tasks;
    /** Persistence service used for loading and saving tasks. */
    private Storage storage;

    /** Creates a task list backed by the default data file. */
    public Tasks() {
        this(new Storage(Path.of("data", "dulio.txt")));
    }

    /**
     * Creates a task list using the supplied persistence service.
     *
     * @param storage the persistence service to use
     */
    public Tasks(Storage storage) {
        this.storage = storage;
        tasks = storage.load();
    }

    /**
     * Adds and persists a task.
     *
     * @param task the task to add
     * @throws IOException if the task list cannot be saved
     */
    public void store(Task task) throws IOException {
        tasks.add(task);
        storage.save(tasks);
    }

    /**
     * Returns the number of tasks.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Formats all tasks as numbered display lines.
     *
     * @return the formatted task list
     */
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

    /**
     * Marks a numbered task as completed and persists the change.
     *
     * @param taskNumber the one-based task number
     * @return the marked task, or null if the number is invalid
     * @throws IOException if the task list cannot be saved
     */
    public Task mark(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsDone();
        storage.save(tasks);
        return tasks.get(idx);
    }

    /**
     * Marks a numbered task as incomplete and persists the change.
     *
     * @param taskNumber the one-based task number
     * @return the unmarked task, or null if the number is invalid
     * @throws IOException if the task list cannot be saved
     */
    public Task unmark(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsNotDone();
        storage.save(tasks);
        return tasks.get(idx);
    }

    /**
     * Deletes and returns a numbered task, or null for an invalid number.
     *
     * @param taskNumber the one-based task number
     * @return the deleted task, or null if the number is invalid
     * @throws IOException if the task list cannot be saved
     */
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
