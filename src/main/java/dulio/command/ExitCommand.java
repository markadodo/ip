package dulio.command;
import java.io.IOException;

import dulio.task.Tasks;
import dulio.ui.Ui;

/** Ends the Dulio session. */
public class ExitCommand extends Command {
    /** Creates an exit command. */
    public ExitCommand() {
    }

    /** Displays the goodbye message.
     * @param tasks unused task list
     * @param ui the interface used for the response
     * @throws IOException if persistence fails
     */
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        ui.showGoodbye();
    }

    /** @return true because this command ends the session */
    @Override
    public boolean isExit() {
        return true;
    }
}