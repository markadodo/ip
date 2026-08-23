import java.io.IOException;
import java.util.Scanner;

public class Dulio {
    public static void main(String[] args) throws IOException {
        String banner = " ____  _   _ _     ___ ___  \n"
            + "|  _ \\| | | | |   |_ _/ _ \\ \n"
            + "| | | | | | | |    | | | | |\n"
            + "| |_| | |_| | |___ | | |_| |\n"
            + "|____/ \\___/|_____|___\\___/ \n";
        System.out.println("____________________________________________________________");
        System.out.println(banner);
        System.out.println("Hello! I'm Dulio.");
        System.out.println("What can I do for you?");

        Tasks tasks = new Tasks();
        runChat(tasks);
    }

    private static void runChat(Tasks tasks) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        while (true) {
            if (!sc.hasNextLine()) {
                break;
            }
            String line = sc.nextLine();
            System.out.println("____________________________________________________________");

            // Hardcoded command handling
            if ("bye".equals(line)) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                sc.close();
                return;
            } else if ("list".equals(line)) {
                String listed = tasks.list();
                System.out.println(" Here are the tasks in your list:");
                if (listed.isEmpty()) {
                    System.out.println(" (no tasks)");
                } else {
                    System.out.print(listed);
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("delete ")) {
                String arg = line.substring(7).trim();
                try {
                    int n = Integer.parseInt(arg);
                    Task t = tasks.delete(n);
                    if (t == null) {
                        System.out.println(" Invalid task index");
                    } else {
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   [" + t.getTypeIcon() + "][" + t.getStatusIcon() + "] " + t);
                        System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid task index");
                }
                System.out.println("____________________________________________________________");
            } else if (line.startsWith("mark ")) {
                String arg = line.substring(5).trim();
                try {
                    int n = Integer.parseInt(arg);
                    Task t = tasks.mark(n);
                    if (t == null) {
                        System.out.println(" Invalid task index");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   [" + t.getTypeIcon() + "][" + t.getStatusIcon() + "] " + t.toString());
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid task index");
                    System.out.println("____________________________________________________________");
                }
            } else if (line.startsWith("unmark ")) {
                String arg = line.substring(7).trim();
                try {
                    int n = Integer.parseInt(arg);
                    Task t = tasks.unmark(n);
                    if (t == null) {
                        System.out.println(" Invalid task index");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   [" + t.getTypeIcon() + "][" + t.getStatusIcon() + "] " + t.toString());
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid task index");
                    System.out.println("____________________________________________________________");
                }
            } else {
                try {
                    Task task = Parser.parseTask(line);
                    tasks.store(task);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   [" + task.getTypeIcon() + "][" + task.getStatusIcon() + "] " + task);
                    System.out.println("   Now you have " + tasks.size() + " tasks in the list.");
                } catch (DulioException e) {
                    System.out.println(" " + e.getMessage());
                }
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }

}
