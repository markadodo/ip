import java.io.IOException;

/** Displays all tasks in the task list. */
public class ListCommand extends Command {
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        ui.showList(tasks.list());
    }
}