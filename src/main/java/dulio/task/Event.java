package dulio.task;
/**
 * A task that occurs between a specified start and end date or time.
 */
public class Event extends Task {
    /** The date or time when the event starts. */
    private String from;
    /** The date or time when the event ends. */
    private String to;

    /**
     * Creates an event task.
     *
     * @param description the event description
     * @param from the date or time when the event starts
     * @param to the date or time when the event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the display icon for an event.
     *
     * @return the event icon
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Returns the event description together with its start and end times.
     *
     * @return the formatted event
     */
    @Override
    public String toString() {
        return description + " (from: " + from + " to: " + to + ")";
    }
}
