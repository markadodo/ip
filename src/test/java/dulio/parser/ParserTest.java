package dulio.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dulio.command.AddCommand;
import dulio.command.DeleteCommand;
import dulio.command.ExitCommand;
import dulio.command.ListCommand;
import dulio.command.MarkCommand;
import dulio.command.UnmarkCommand;
import dulio.exception.DulioException;
import dulio.task.Deadline;
import dulio.task.Event;
import dulio.task.Task;
import dulio.task.Todo;

/** Tests command and date parsing behavior. */
public class ParserTest {
    @Test
    public void parseDate_validIsoDate_returnsLocalDate() throws DulioException {
        assertEquals(LocalDate.of(2019, 10, 15), Parser.parseDate("2019-10-15"));
    }

    @Test
    public void parseDate_leapYearDate_returnsLocalDate() throws DulioException {
        assertEquals(LocalDate.of(2020, 2, 29), Parser.parseDate("2020-02-29"));
    }

    @Test
    public void parseDate_invalidFormat_throwsDulioException() {
        DulioException exception = assertThrows(DulioException.class,
            () -> Parser.parseDate("15/10/2019"));
        assertEquals("OOPS!!! Please enter dates in yyyy-MM-dd format.", exception.getMessage());
    }

    @Test
    public void parseDate_impossibleDate_throwsDulioException() {
        assertThrows(DulioException.class, () -> Parser.parseDate("2019-02-29"));
    }

    @Test
    public void parseTask_todoCommand_returnsTodo() throws DulioException {
        Task task = Parser.parseTask("todo borrow book");
        assertInstanceOf(Todo.class, task);
        assertEquals("borrow book", task.getDescription());
    }

    @Test
    public void parseTask_deadlineCommand_returnsDeadlineWithDate() throws DulioException {
        Task task = Parser.parseTask("deadline return book /by 2019-10-15");
        Deadline deadline = assertInstanceOf(Deadline.class, task);
        assertEquals(LocalDate.of(2019, 10, 15), deadline.getBy());
    }

    @Test
    public void parseTask_eventCommand_returnsEvent() throws DulioException {
        Task task = Parser.parseTask("event project meeting /from Mon /to Tue");
        Event event = assertInstanceOf(Event.class, task);
        assertEquals("Mon", event.getFrom());
        assertEquals("Tue", event.getTo());
    }

    @Test
    public void parseTask_emptyTodo_throwsDulioException() {
        assertThrows(DulioException.class, () -> Parser.parseTask("todo"));
    }

    @Test
    public void parseTask_unknownCommand_throwsDulioException() {
        assertThrows(DulioException.class, () -> Parser.parseTask("blah"));
    }

    @Test
    public void parseTask_deadlineWithoutByDate_throwsDulioException() {
        assertThrows(DulioException.class,
            () -> Parser.parseTask("deadline return book"));
    }

    @Test
    public void parseTask_eventWithoutEndTime_throwsDulioException() {
        assertThrows(DulioException.class,
            () -> Parser.parseTask("event project meeting /from Mon"));
    }

    @Test
    public void parseCommand_bye_returnsExitCommand() throws DulioException {
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("bye"));
    }

    @Test
    public void parseCommand_list_returnsListCommand() throws DulioException {
        assertInstanceOf(ListCommand.class, Parser.parseCommand("list"));
    }

    @Test
    public void parseCommand_delete_returnsDeleteCommand() throws DulioException {
        assertInstanceOf(DeleteCommand.class, Parser.parseCommand("delete 2"));
    }

    @Test
    public void parseCommand_mark_returnsMarkCommand() throws DulioException {
        assertInstanceOf(MarkCommand.class, Parser.parseCommand("mark 2"));
    }

    @Test
    public void parseCommand_unmark_returnsUnmarkCommand() throws DulioException {
        assertInstanceOf(UnmarkCommand.class, Parser.parseCommand("unmark 2"));
    }

    @Test
    public void parseCommand_taskCommand_returnsAddCommand() throws DulioException {
        assertInstanceOf(AddCommand.class, Parser.parseCommand("todo borrow book"));
    }

    @Test
    public void parseCommand_nonNumericTaskNumber_throwsDulioException() {
        assertThrows(DulioException.class, () -> Parser.parseCommand("delete two"));
    }

    @Test
    public void parseCommand_nonNumericMarkNumber_throwsDulioException() {
        DulioException exception = assertThrows(DulioException.class,
            () -> Parser.parseCommand("mark one"));
        assertEquals("Invalid task index", exception.getMessage());
    }

    @Test
    public void parseCommand_nonNumericUnmarkNumber_throwsDulioException() {
        DulioException exception = assertThrows(DulioException.class,
            () -> Parser.parseCommand("unmark one"));
        assertEquals("Invalid task index", exception.getMessage());
    }

    @Test
    public void parseCommand_negativeTaskNumber_returnsCommand() throws DulioException {
        assertInstanceOf(DeleteCommand.class, Parser.parseCommand("delete -1"));
    }
}
