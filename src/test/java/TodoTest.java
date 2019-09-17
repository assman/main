import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import command.AddCommand;
import task.*;
import ui.*;

/**
 * This class implements the unit testing code for the To-do class.
 *
 * @author Sai Ganesh Suresh
 * @version v1.0
 */
public class TodoTest {

    Todo todo = new Todo("testing todo");

    @Test
    public void testTodoCreation() {
        String title = todo.description;
        Assertions.assertEquals(title, "testing todo");
        Assertions.assertEquals(todo.toString(), "[T][" + "\u2718" + "] testing todo");
    }

    @Test
    public void testTodoWithinPeriod() throws ParseException {
        Date from = new SimpleDateFormat("dd/MM/yyyy").parse("17/09/2019");
        Date to = new SimpleDateFormat("dd/MM/yyyy").parse("18/09/2019");
        Todo newTodo = new Todo("testTodo", from, to);
        Assertions.assertEquals(newTodo.getFrom(), from);
    }
}