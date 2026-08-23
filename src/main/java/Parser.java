import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Parses Dulio commands and date values. */
public class Parser {
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

    private static DulioException unknownCommand() {
        return new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}