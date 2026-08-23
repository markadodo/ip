package dulio.command;

import java.io.IOException;

import dulio.task.Tasks;
import dulio.ui.Ui;

/** Displays all tasks in the task list. */
public class ListCommand extends Command {
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        ui.showList(tasks.list());
    }
}