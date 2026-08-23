package dulio.command;
import java.io.IOException;

import dulio.task.Task;
import dulio.task.Tasks;
import dulio.ui.Ui;

/** Deletes a task by its one-based list number. */
public class DeleteCommand extends Command {
    /** One-based number of the task to delete. */
    private int taskNumber;

    /** Creates a delete command.
     * @param taskNumber the one-based task number
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /** Deletes the task and reports the result.
     * @param tasks the task list to modify
     * @param ui the interface used for the response
     * @throws IOException if persistence fails
     */
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        Task task = tasks.delete(taskNumber);
        if (task == null) {
            ui.showInvalidIndex();
        } else {
            ui.showDeleted(task, tasks.size());
        }
    }
}