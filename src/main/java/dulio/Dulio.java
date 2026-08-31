package dulio;

import java.io.IOException;

import dulio.command.Command;
import dulio.exception.DulioException;
import dulio.parser.Parser;
import dulio.task.Tasks;
import dulio.gui.GuiUi;
import dulio.ui.Ui;

/** Coordinates Dulio's user interface, parser, commands, and task list. */
public class Dulio {
    /**
     * Starts a Dulio session.
     *
     * @param args command-line arguments, which are not used
     * @throws IOException if task data cannot be saved
     */
    public static void main(String[] args) throws IOException {
        new Dulio().run();
    }

    private Ui ui;
    private Tasks tasks;

    /** Creates a Dulio application with its default UI and task list. */
    public Dulio() {
        ui = new Ui();
        tasks = new Tasks();
    }

    /**
     * Runs the command-processing loop until the user exits or input ends.
     *
     * @throws IOException if task data cannot be saved
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String line = ui.readCommand();
            if (line == null) {
                break;
            }
            try {
                Command command = Parser.parseCommand(line);
                command.execute(tasks, ui);
                isExit = command.isExit();
            } catch (DulioException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * Processes one command and returns Dulio's response for a graphical interface.
     *
     * @param input the command to process
     * @return Dulio's response text
     */
    public String getResponse(String input) {
        GuiUi guiUi = new GuiUi();
        try {
            Command command = Parser.parseCommand(input);
            command.execute(tasks, guiUi);
        } catch (DulioException e) {
            guiUi.showError(e.getMessage());
        } catch (IOException e) {
            guiUi.showError("Unable to save your task right now.");
        }
        return guiUi.getResponse();
    }

}
