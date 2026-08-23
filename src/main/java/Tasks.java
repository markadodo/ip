import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tasks {
    private ArrayList<Task> tasks;
    private Path storagePath;

    public Tasks() {
        tasks = new ArrayList<>();
        storagePath = Path.of("data", "dulio.txt");
        load();
    }

    public void store(Task task) throws IOException {
        tasks.add(task);
        save();
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
        save();
        return tasks.get(idx);
    }

    public Task unmark(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        tasks.get(idx).markAsNotDone();
        save();
        return tasks.get(idx);
    }

    public Task delete(int taskNumber) throws IOException {
        int idx = taskNumber - 1;
        if (idx < 0 || idx >= tasks.size()) {
            return null;
        }
        Task deletedTask = tasks.remove(idx);
        save();
        return deletedTask;
    }

    private void load() {
        if (!Files.exists(storagePath)) {
            return;
        }
        try {
            for (String line : Files.readAllLines(storagePath)) {
                String[] fields = line.split("\\s\\|\\s", -1);
                if (fields.length < 3 || !(fields[1].equals("0") || fields[1].equals("1"))) {
                    continue;
                }
                Task task;
                if (fields[0].equals("D") && fields.length == 4) {
                    task = new Deadline(fields[2], fields[3]);
                } else if (fields[0].equals("E") && fields.length == 5) {
                    task = new Event(fields[2], fields[3], fields[4]);
                } else if (fields[0].equals("T") && fields.length == 3) {
                    task = new Todo(fields[2]);
                } else {
                    continue;
                }
                if (fields[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            tasks.clear();
        }
    }

    private void save() throws IOException {
        Path parent = storagePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            String status = task.isDone() ? "1" : "0";
            if (task instanceof Deadline deadline) {
                lines.add("D | " + status + " | " + deadline.description + " | " + deadline.getBy());
            } else if (task instanceof Event event) {
                lines.add("E | " + status + " | " + event.description + " | "
                    + event.getFrom() + " | " + event.getTo());
            } else {
                lines.add("T | " + status + " | " + task.description);
            }
        }
        Files.write(storagePath, lines);
    }
}
