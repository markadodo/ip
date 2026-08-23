package dulio.command;
import java.io.IOException;

import dulio.task.Task;
import dulio.task.Tasks;
import dulio.ui.Ui;

/** Adds a parsed task to the task list. */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        tasks.store(task);
        ui.showAdded(task, tasks.size());
    }
}