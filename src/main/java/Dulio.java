public class Dulio {
    public static void main(String[] args) {
        String banner = " ____  _   _ _     ___ ___  \n"
            + "|  _ \\| | | | |   |_ _/ _ \\ \n"
            + "| | | | | | | |    | | | | |\n"
            + "| |_| | |_| | |___ | | |_| |\n"
            + "|____/ \\___/|_____|___\\___/ \n";
        System.out.println("____________________________________________________________");
        System.out.println(banner);
        System.out.println("Hello! I'm Dulio.");
        System.out.println("What can I do for you?");

        // in-memory storage for up to 100 tasks
        Tasks tasks = new Tasks(100);
        java.util.Scanner sc = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("____________________________________________________________");
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
                        System.out.println("   [" + t.getStatusIcon() + "] " + t.toString());
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
                        System.out.println("   [" + t.getStatusIcon() + "] " + t.toString());
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Invalid task index");
                    System.out.println("____________________________________________________________");
                }
            } else {
                // store any other input as a task
                tasks.store(line);
                System.out.println(" added: " + line);
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }
}
