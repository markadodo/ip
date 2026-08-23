package dulio.command;
import java.io.IOException;

import dulio.task.Task;
import dulio.task.Tasks;
import dulio.ui.Ui;

/** Adds a parsed task to the task list. */
public class AddCommand extends Command {
    /** The task to add. */
    private Task task;

    /** Creates an add command for the supplied task.
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /** Adds the task and reports the updated count.
     * @param tasks the task list to modify
     * @param ui the interface used for the response
     * @throws IOException if persistence fails
     */
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        tasks.store(task);
        ui.showAdded(task, tasks.size());
    }
}