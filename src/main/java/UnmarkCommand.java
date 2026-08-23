import java.io.IOException;

/** Marks a task as not completed. */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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