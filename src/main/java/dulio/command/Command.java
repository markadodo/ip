package dulio.command;
import java.io.IOException;

import dulio.task.Tasks;
import dulio.ui.Ui;

/** Represents a parsed command that can be executed by Dulio. */
public abstract class Command {
    /** Creates a command. */
    protected Command() {
    }

    /**
     * Executes this command using the task list and user interface.
     * @param tasks the task list to modify or display
     * @param ui the interface used for responses
     * @throws IOException if persistence fails
     */
    public abstract void execute(Tasks tasks, Ui ui) throws IOException;

    /**
     * Indicates whether this command ends the application.
     * @return true when the application should exit
     */
    public boolean isExit() {
        return false;
    }
}