import java.util.Scanner;

/** Handles Dulio's interaction with the console user. */
public class Ui {
    private Scanner scanner;

    /** Creates a console UI that reads from standard input. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /** Displays the startup greeting and command separator. */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" ____  _   _ _     ___ ___  \n"
            + "|  _ \\| | | | |   |_ _/ _ \\ \n"
            + "| | | | | | | |    | | | | |\n"
            + "| |_| | |_| | |___ | | |_| |\n"
            + "|____/ \\___/|_____|___\\___/ \n");
        System.out.println("Hello! I'm Dulio.");
        System.out.println("What can I do for you?");
        showSeparator();
    }

    /** Reads the next command, or returns null when input ends. */
    public String readCommand() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        String command = scanner.nextLine();
        showSeparator();
        return command;
    }

    /** Displays a separator line. */
    public void showSeparator() {
        System.out.println("____________________________________________________________");
    }

    /** Closes the console input. */
    public void close() {
        scanner.close();
    }

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
        showSeparator();
    }

    public void showList(String listed) {
        System.out.println(" Here are the tasks in your list:");
        if (listed.isEmpty()) {
            System.out.println(" (no tasks)");
        } else {
            System.out.print(listed);
        }
        showSeparator();
    }

    public void showInvalidIndex() {
        System.out.println(" Invalid task index");
        showSeparator();
    }

    public void showAdded(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   [" + task.getTypeIcon() + "][" + task.getStatusIcon() + "] " + task);
        System.out.println("   Now you have " + taskCount + " tasks in the list.");
        showSeparator();
    }

    public void showDeleted(Task task, int taskCount) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   [" + task.getTypeIcon() + "][" + task.getStatusIcon() + "] " + task);
        System.out.println("   Now you have " + taskCount + " tasks in the list.");
        showSeparator();
    }

    public void showMarked(Task task, boolean marked) {
        System.out.println(marked
            ? " Nice! I've marked this task as done:"
            : " OK, I've marked this task as not done yet:");
        System.out.println("   [" + task.getTypeIcon() + "][" + task.getStatusIcon() + "] " + task);
        showSeparator();
    }

    public void showError(String message) {
        System.out.println(" " + message);
        showSeparator();
    }
}