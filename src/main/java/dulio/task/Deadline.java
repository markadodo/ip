package dulio.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    /** The date or time by which the task should be completed. */
    private LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param description the task description
    * @param by the date by which the task should be completed
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the display icon for a deadline.
     *
     * @return the deadline icon
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the task description together with its deadline.
     *
     * @return the formatted deadline
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        return description + " (by: " + formattedDate + ")";
    }
}
