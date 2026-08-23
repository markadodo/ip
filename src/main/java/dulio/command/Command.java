package dulio.command;
import java.io.IOException;

import dulio.task.Tasks;
import dulio.ui.Ui;

/** Represents a parsed command that can be executed by Dulio. */
public abstract class Command {
    /** Executes this command using the task list and user interface. */
    public abstract void execute(Tasks tasks, Ui ui) throws IOException;

    /** Indicates whether this command ends the application. */
    public boolean isExit() {
        return false;
    }
}