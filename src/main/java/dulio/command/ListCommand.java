package dulio.command;

import java.io.IOException;

import dulio.task.Tasks;
import dulio.ui.Ui;

/** Displays all tasks in the task list. */
public class ListCommand extends Command {
    /** Creates a list command. */
    public ListCommand() {
    }

    /** Displays all tasks.
     * @param tasks the task list to display
     * @param ui the interface used for the response
     * @throws IOException if persistence fails
     */
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        ui.showList(tasks.list());
    }
}