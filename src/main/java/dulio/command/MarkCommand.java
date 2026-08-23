package dulio.command;

import java.io.IOException;

import dulio.task.Task;
import dulio.task.Tasks;
import dulio.ui.Ui;

/** Marks a task as completed. */
public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        Task task = tasks.mark(taskNumber);
        if (task == null) {
            ui.showInvalidIndex();
        } else {
            ui.showMarked(task, true);
        }
    }
}