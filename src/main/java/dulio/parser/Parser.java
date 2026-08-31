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

/** Parses Dulio commands and date values. */
public class Parser {
    
    private Parser() {
    }

    /**
     * Converts an ISO date string into a date.
     *
     * @param date the date in yyyy-MM-dd format
     * @return the parsed date
     * @throws DulioException if the date is not valid
     */
    public static LocalDate parseDate(String date) throws DulioException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DulioException("OOPS!!! Please enter dates in yyyy-MM-dd format.");
        }
    }

    /**
     * Converts a console command into a task.
     *
     * @param line the console command
     * @return the parsed task
     * @throws DulioException if the command is invalid
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
     * Converts a complete console line into an executable command.
     *
     * @param line the complete console command
     * @return the corresponding command object
     * @throws DulioException if the command or its arguments are invalid
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

    private static DulioException unknownCommand() {
        return new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}