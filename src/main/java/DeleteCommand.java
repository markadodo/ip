import java.io.IOException;

/** Deletes a task by its one-based list number. */
public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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