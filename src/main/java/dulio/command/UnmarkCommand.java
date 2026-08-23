package dulio.command;
import java.io.IOException;

import dulio.task.Task;
import dulio.task.Tasks;
import dulio.ui.Ui;

/** Marks a task as not completed. */
public class UnmarkCommand extends Command {
    /** One-based number of the task to unmark. */
    private int taskNumber;

    /** Creates an unmark command.
     * @param taskNumber the one-based task number
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /** Marks the task as incomplete and reports the result.
     * @param tasks the task list to modify
     * @param ui the interface used for the response
     * @throws IOException if persistence fails
     */
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        Task task = tasks.unmark(taskNumber);
        if (task == null) {
            ui.showInvalidIndex();
        } else {
            ui.showMarked(task, false);
        }
    }
}