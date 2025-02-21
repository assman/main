package ui;

import task.Task;
import task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class is used to handle all inputs and outputs used to interact with
 * the end-user.
 *
 * @author Sai Ganesh Suresh
 * @version v1.0
 */
public class Ui {

    // All the different definitions including the string definitions are here.
    public static String userOutputForUI;

    private static String userOutputDash = "_______________________________\n";

    private static String userInput;

    private static String goodbye = "_______________________________\n" + "Bye. Hope to see you again soon!\n"
        + "_______________________________\n";

    private static String greeting = "_______________________________\n" + "Hello! I'm Duke\n"
        + "What can I do for you?\n" + "_______________________________\n";

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prints a 'dashed' line.
     */
    public static void printDash() {
        System.out.println(userOutputDash);
    }

    /**
     * Prints greeting.
     */
    public static void printGreeting() {
        System.out.println(greeting);
    }

    /**
     * Prints the reminders triggered.
     */
    public static void printReminder(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        System.out.println("You have these upcoming tasks:\n");
        for (Task t : taskList) {
            if (t.checkReminderTrigger()) {
                System.out.println(t.toString());
            }
        }
    }

    public static void printGoodbye() {
        System.out.println(goodbye);
    }

    /**
     * This printOutput function is used to print the output in the current Ui
     * format. Moreover it also handles the refresh of the userOutputForUT.
     *
     * @param userOutput This string will be used to print the message between to
     *                   data.
     */

    public static void printOutput(String userOutput) {
        Ui.userOutputForUI = userOutput + "\n";
        System.out.println(userOutputDash);
        System.out.println(userOutput);
        System.out.println(userOutputDash);
    }

    /**
     * This readInput function is used to constantly take in the userInput by
     * checking if there is a line to be read. But if there is no line to be read
     * then the program terminates.
     *
     * @return This function will return a String which contains the user input.
     */
    public static String readInput() {
        if (scanner.hasNextLine()) {
            userInput = scanner.nextLine();
        } else {
            userInput = "bye";
        }
        return userInput;
    }

    /**
     * This printMessage function is called to print an exception message to the
     * user.
     *
     * @param message This message contains the message generated by the exception
     *                that was caught.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * This printManual function is called to aid the user in the usage of 'Duke' by
     * guiding them on how to input their task.
     */
    public static void printManual() {
        System.out.println(userOutputDash);
        System.out.println("Hi, this is Duke!");
        System.out.println("I am able to let you add tasks of three types! todo, event and deadline");
        System.out.println("1. To add a todo task, key in todo<space>your task description eg. todo borrow books");
        System.out.println("2. To add a deadline, key in deadline<space>your deadline description<space>/by<space>the "
            + "date eg. return books/by dd/MM/yyyy HHmm");
        System.out.println("3. To add a event, key in event<space>your event description<space>/at<space>the day or "
            + "date and the time eg. Project meeting /at dd/MM/yyyy HHmm-HHmm");
        System.out.println("4. To list out all your tasks simply enter list");
        System.out.println("5. To mark a task as complete just enter done<space>the index of the task as listed");
        System.out.println("6. To find a task from the task list enter find<space>any word in the task");
        System.out.println("7. To delete a task just enter delete<space>the index of the task as listed");
        System.out.println("8. To exit, enter bye");
    }

    public static void printUnknownInput() {
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means. If you would like to know how to "
            + "use Duke, Enter duke-manual");
    }
}
