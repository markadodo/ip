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
