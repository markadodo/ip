# Dulio UI Test Plan

The `test-ui` skill runs these cases from the repository root. Inputs are supplied to `Dulio` through standard input. Expected output is compared exactly after converting CRLF to LF and trimming trailing whitespace from each line.

## Case: add-and-list-task-types

### Aim
Verify that todo, deadline, and event commands create the correct task types, preserve their date/time strings, report the task count, and display correctly in the list.

### Inputs
1. `todo borrow book`
2. `deadline return book /by Sunday`
3. `event project meeting /from Mon 2pm /to 4pm`
4. `list`
5. `bye`

### Expected output
```text
 ____  _   _ _     ___ ___
|  _ \| | | | |   |_ _/ _ \
| | | | | | | |    | | | | |
| |_| | |_| | |___ | | |_| |
|____/ \___/|_____|___\___/

Hello! I'm Dulio.
What can I do for you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] borrow book
   Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sunday)
   Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
   Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] return book (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## Case: reject-empty-and-unknown-commands

### Aim
Verify that an empty todo description and an unknown command produce clear error messages while the program continues accepting commands.

### Inputs
1. `todo`
2. `blah`
3. `bye`

### Expected output
```text
 ____  _   _ _     ___ ___
|  _ \| | | | |   |_ _/ _ \
| | | | | | | |    | | | | |
| |_| | |_| | |___ | | |_| |
|____/ \___/|_____|___\___/

Hello! I'm Dulio.
What can I do for you?
____________________________________________________________
____________________________________________________________
 OOPS!!! The description of a todo cannot be empty.
____________________________________________________________
____________________________________________________________
 OOPS!!! I'm sorry, but I don't know what that means :-(
____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```

## Case: delete-task

### Aim
Verify that deleting a task by its list number removes the correct typed task, shifts later tasks up, and reports the updated task count.

### Inputs
1. `todo read book`
2. `deadline return book /by June 6th`
3. `event project meeting /from Aug 6th 2pm /to 4pm`
4. `todo join sports club`
5. `todo borrow book`
6. `mark 1`
7. `mark 2`
8. `mark 4`
9. `list`
10. `delete 3`
11. `list`
12. `bye`

### Expected output
```text
 ____  _   _ _     ___ ___
|  _ \| | | | |   |_ _/ _ \
| | | | | | | |    | | | | |
| |_| | |_| | |___ | | |_| |
|____/ \___/|_____|___\___/

Hello! I'm Dulio.
What can I do for you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
   Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: June 6th)
   Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
   Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] join sports club
   Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] borrow book
   Now you have 5 tasks in the list.
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [D][X] return book (by: June 6th)
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] join sports club
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read book
 2.[D][X] return book (by: June 6th)
 3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
 4.[T][X] join sports club
 5.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
   Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][X] read book
 2.[D][X] return book (by: June 6th)
 3.[T][X] join sports club
 4.[T][ ] borrow book
____________________________________________________________
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```
