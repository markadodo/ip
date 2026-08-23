import java.io.IOException;
public class Dulio {
    public static void main(String[] args) throws IOException {
        new Dulio().run();
    }

    private Ui ui;
    private Tasks tasks;

    public Dulio() {
        ui = new Ui();
        tasks = new Tasks();
    }

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

}
