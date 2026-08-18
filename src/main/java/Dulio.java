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

            // Hardcoded switch behavior
            switch (line) {
                case "bye":
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    sc.close();
                    return;
                case "list":
                    String listed = tasks.list();
                    if (listed.isEmpty()) {
                        // no tasks
                        System.out.println(" (no tasks)");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.print(listed);
                        System.out.println("____________________________________________________________");
                    }
                    break;
                default:
                    // store any other input as a task
                    tasks.store(line);
                    System.out.println(" added: " + line);
                    System.out.println("____________________________________________________________");
                    break;
            }
        }
        sc.close();
    }
}
