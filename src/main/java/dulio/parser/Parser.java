package dulio.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dulio.command.AddCommand;
import dulio.command.Command;
import dulio.command.DeleteCommand;
import dulio.command.ExitCommand;
import dulio.command.FindCommand;
import dulio.command.ListCommand;
import dulio.command.MarkCommand;
import dulio.command.UnmarkCommand;
import dulio.exception.DulioException;
import dulio.task.Deadline;
import dulio.task.Event;
import dulio.task.Task;
import dulio.task.Todo;

/**
 * Parses Dulio commands and date values.
 */
public class Parser {

    private Parser() {
    }

    /**
     * Returns the date represented by an ISO date string.
     *
     * @param date The date in yyyy-MM-dd format.
     * @return The parsed date.
     * @throws DulioException If the date is not valid.
     */
    public static LocalDate parseDate(String date) throws DulioException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DulioException("OOPS!!! Please enter dates in yyyy-MM-dd format.");
        }
    }

    /**
     * Returns the task represented by a console command.
     *
     * @param line The console command.
     * @return The parsed task.
     * @throws DulioException If the command is invalid.
     */
    public static Task parseTask(String line) throws DulioException {
        if (line.equals("todo") || line.startsWith("todo ")) {
            String description = line.length() > 5 ? line.substring(5).trim() : "";
            if (description.isEmpty()) {
                throw new DulioException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(description);
        }
        if (line.startsWith("deadline ")) {
            int marker = line.indexOf(" /by ", 9);
            if (marker < 0) {
                throw unknownCommand();
            }
            String description = line.substring(9, marker).trim();
            String date = line.substring(marker + 5).trim();
            if (description.isEmpty() || date.isEmpty()) {
                throw unknownCommand();
            }
            return new Deadline(description, parseDate(date));
        }
        if (line.startsWith("event ")) {
            int fromMarker = line.indexOf(" /from ", 6);
            int toMarker = fromMarker < 0 ? -1 : line.indexOf(" /to ", fromMarker + 7);
            if (fromMarker < 0 || toMarker < 0) {
                throw unknownCommand();
            }
            String description = line.substring(6, fromMarker).trim();
            String from = line.substring(fromMarker + 7, toMarker).trim();
            String to = line.substring(toMarker + 5).trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw unknownCommand();
            }
            return new Event(description, from, to);
        }
        throw unknownCommand();
    }

    /**
     * Returns the task represented by a local storage record.
     *
     * @param line The stored task record.
     * @return The parsed task, or null if the record is invalid.
     */
    public static Task parseStoredTask(String line) {
        String[] fields = line.split("\\s\\|\\s", -1);
        if (fields.length < 3 || !(fields[1].equals("0") || fields[1].equals("1"))) {
            return null;
        }

        Task task;
        try {
            task = createStoredTask(fields);
        } catch (DateTimeParseException e) {
            return null;
        }
        if (task == null) {
            return null;
        }

        if (fields[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns the executable command represented by a complete console line.
     *
     * @param line The complete console command.
     * @return The corresponding command object.
     * @throws DulioException If the command or its arguments are invalid.
     */
    public static Command parseCommand(String line) throws DulioException {
        if ("bye".equals(line)) {
            return new ExitCommand();
        }
        if ("list".equals(line)) {
            return new ListCommand();
        }
        if (line.startsWith("find ")) {
            String keyword = line.substring(5).trim();
            if (keyword.isEmpty()) {
                throw unknownCommand();
            }
            return new FindCommand(keyword);
        }
        if (line.startsWith("delete ")) {
            return new DeleteCommand(parseTaskNumber(line.substring(7).trim()));
        }
        if (line.startsWith("mark ")) {
            return new MarkCommand(parseTaskNumber(line.substring(5).trim()));
        }
        if (line.startsWith("unmark ")) {
            return new UnmarkCommand(parseTaskNumber(line.substring(7).trim()));
        }
        return new AddCommand(parseTask(line));
    }

    private static int parseTaskNumber(String value) throws DulioException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new DulioException("Invalid task index");
        }
    }

    private static Task createStoredTask(String[] fields) {
        if (fields[0].equals("D") && fields.length == 4) {
            return new Deadline(fields[2], LocalDate.parse(fields[3]));
        } else if (fields[0].equals("E") && fields.length == 5) {
            return new Event(fields[2], fields[3], fields[4]);
        } else if (fields[0].equals("T") && fields.length == 3) {
            return new Todo(fields[2]);
        }
        return null;
    }

    private static DulioException unknownCommand() {
        return new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
