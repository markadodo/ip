/**
 * A task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    /** The date or time by which the task should be completed. */
    private String by;

    /**
     * Creates a deadline task.
     *
     * @param description the task description
     * @param by the date or time by which the task should be completed
     */
    public Deadline(String description, String by) {
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

    /**
     * Returns the task description together with its deadline.
     *
     * @return the formatted deadline
     */
    @Override
    public String toString() {
        return description + " (by: " + by + ")";
    }
}
