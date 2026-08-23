package dulio.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import dulio.task.Deadline;
import dulio.task.Event;
import dulio.task.Task;
import dulio.task.Todo;

/** Handles loading tasks from and saving tasks to the data file. */
public class Storage {
    /** File used to persist task records. */
    private Path filePath;

    /**
     * Creates storage backed by the specified path.
     *
     * @param filePath the relative or absolute data-file path
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads valid task records, returning an empty list if the file is absent
     * or cannot be read.
     *
     * @return the loaded tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            return loadedTasks;
        }
        try {
            for (String line : Files.readAllLines(filePath)) {
                String[] fields = line.split("\\s\\|\\s", -1);
                if (fields.length < 3 || !(fields[1].equals("0") || fields[1].equals("1"))) {
                    continue;
                }
                Task task;
                try {
                    if (fields[0].equals("D") && fields.length == 4) {
                        task = new Deadline(fields[2], LocalDate.parse(fields[3]));
                    } else if (fields[0].equals("E") && fields.length == 5) {
                        task = new Event(fields[2], fields[3], fields[4]);
                    } else if (fields[0].equals("T") && fields.length == 3) {
                        task = new Todo(fields[2]);
                    } else {
                        continue;
                    }
                } catch (DateTimeParseException e) {
                    continue;
                }
                if (fields[1].equals("1")) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return loadedTasks;
    }

    /**
     * Saves all task records, creating the parent directory when necessary.
     *
     * @param tasks the tasks to persist
     * @throws IOException if the file cannot be written
     */
    public void save(List<Task> tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            String status = task.isDone() ? "1" : "0";
            if (task instanceof Deadline deadline) {
                lines.add("D | " + status + " | " + deadline.getDescription() + " | " + deadline.getBy());
            } else if (task instanceof Event event) {
                lines.add("E | " + status + " | " + event.getDescription() + " | "
                    + event.getFrom() + " | " + event.getTo());
            } else {
                lines.add("T | " + status + " | " + task.getDescription());
            }
        }
        Files.write(filePath, lines);
    }
}