package task;
import java.io.Serializable;  
import java.util.Date;
/**
 * This extension of the task class will allow the user to add a task of to-do type.
 *
 * @author Sai Ganesh Suresh
 * @version v2.0
 */
public class Todo extends Task implements Serializable {
    protected Date from;
    protected Date to;

    public Todo(String description){
        super(description);
    }

    public Todo(String description, Date from, Date to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * This override of the toString function of the task class etches the different portions of the user input into a
     * single string.
     *
     * @return This function returns a string of the required task in the desired output format of string type.
     */
    @Override
    public String toString() {
        if(this.from != null && this.to != null){
            return "[T]" + "[" + super.getStatusIcon() + "] " + this.description + " " + " (from: " + from.toString() + ")" + " (to: " + to.toString() + ")";
        }
        else {
            return "[T]" + "[" + super.getStatusIcon() + "] " + this.description;
        }
        
    }
}
