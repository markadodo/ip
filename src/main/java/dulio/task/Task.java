package dulio.task;

/** Represents a task with a description and completion status. */
public class Task {
    /** The task description. */
    protected String description;
    /** Whether the task has been completed. */
    protected boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as completed. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the display icon for the completion status.
     *
     * @return {@code X} when complete, otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns whether this task is completed.
     *
     * @return true when this task is complete
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the display icon for a basic todo task.
     *
     * @return the todo icon
     */
    public String getTypeIcon() {
        return "T";
    }

    @Override
    /**
     * Returns the task description for display.
     *
     * @return the task description
     */
    public String toString() {
        return description;
    }
}
