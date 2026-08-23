import java.io.IOException;

/** Ends the Dulio session. */
public class ExitCommand extends Command {
    @Override
    public void execute(Tasks tasks, Ui ui) throws IOException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}