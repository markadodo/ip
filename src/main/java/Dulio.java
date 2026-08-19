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
                    Task task = parseTask(line);
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

    private static Task parseTask(String line) throws DulioException {
        if (line.equals("todo") || line.startsWith("todo ")) {
            String description = line.length() > 5 ? line.substring(5).trim() : "";
            if (description.isEmpty()) {
                throw new DulioException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Todo(description);
        }
        if (line.startsWith("deadline ")) {
            int marker = line.indexOf(" /by ", 9);
            if (marker < 0) {
                throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String description = line.substring(9, marker).trim();
            String by = line.substring(marker + 5).trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return new Deadline(description, by);
        }
        if (line.startsWith("event ")) {
            int fromMarker = line.indexOf(" /from ", 6);
            if (fromMarker < 0) {
                throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            int toMarker = line.indexOf(" /to ", fromMarker + 7);
            if (toMarker < 0) {
                throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String description = line.substring(6, fromMarker).trim();
            String from = line.substring(fromMarker + 7, toMarker).trim();
            String to = line.substring(toMarker + 5).trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return new Event(description, from, to);
        }
        throw new DulioException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
