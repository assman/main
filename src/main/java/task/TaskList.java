package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The TaskList class handles all operations performed on the TaskList as well
 * as stores the TaskList.
 *
 * @author Sai Ganesh Suresh
 * @version v1.0
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * This custom comparator allows the sorting of both deadlines and events.
     *
     * @param task contains the task that needs to be added.
     */
    public static final Comparator<Task> DateComparator = (firstDate, secondDate) -> {
        if (firstDate.startDate == null && secondDate != null) {
            return -1;
        } else if (firstDate.startDate != null && secondDate.startDate == null) {
            return 1;
        }
        if (firstDate.startDate.isBefore(secondDate.startDate)) {
            return -1;
        } else {
            return 1;
        }
    };

    /**
     * This custom comparator allows the sorting of both deadlines and events.
     *
     * @param task contains the task that needs to be added.
     */
    public static final Comparator<Task> PriorityComparator = (firstPriority, secondPriority) -> {
        if (firstPriority.priority.equals(Priority.HIGH) && secondPriority.priority.equals(Priority.MEDIUM)) {
            return -1;
        } else if (firstPriority.priority.equals(Priority.MEDIUM) && secondPriority.priority.equals(Priority.MEDIUM)) {
            return 1;
        } else {
            return 0;
        }
    };

    /**
     * This function allows the use to add a particular task.
     *
     * @param task contains the task that needs to be added.
     */
    public void add(Task task) {
        listOfTasks.add(task);
    }

    /**
     * This function allows the use to delete a particular task.
     *
     * @param indexOfTask this is the index of the task which needs to be deleted.
     */
    public Task delete(int indexOfTask) {
        Task task = listOfTasks.get(indexOfTask);
        listOfTasks.remove(task);
        return task;
    }

    /**
     * This function allows the user to find tasks with a particular keyword.
     *
     * @param keyWord this string contains the keyword the user is searching for.
     */
    public ArrayList<Task> find(String keyWord) {
        ArrayList<Task> holdFoundTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            String findMatch = listOfTasks.get(i).toString();
            if (findMatch.contains(keyWord)) {
                holdFoundTasks.add(listOfTasks.get(i));
            }
        }
        return holdFoundTasks;
    }

    /**
     * Performs a check as to determine if the task being added has a clash with
     * another task already scheduled.
     *
     * @param taskToCheck the task trying to be added by the user.
     * @return boolean true if there is a clash, false if there is not clash.
     */
    public boolean isClash(Task taskToCheck) {
        for (Task task : listOfTasks) {
            if (task.checkForClash(taskToCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function allows the user to mark a particular task as done.
     *
     * @param indexOfTask this is the index of the task which needs to be marked as
     *                    done.
     */
    public Task markAsDone(int indexOfTask) {
        Task task = listOfTasks.get(indexOfTask);
        task.markAsDone();
        return task;
    }

    /**
     * Marks a task to be ignored and have reminders to stop showing up for the
     * task.
     *
     * @param index The index of the task to be marked
     * @return The marked task
     */
    public Task markAsIgnorable(int index) {
        Task task = listOfTasks.get(index);
        task.markAsIgnorable();
        return task;
    }

    /**
     * Marks a task to no longer be ignored and have reminders to show up again.
     *
     * @param index The index of the task to be marked
     * @return The marked task
     */
    public Task markAsUnignorable(int index) {
        Task task = listOfTasks.get(index);
        task.markAsUnignorable();
        return task;
    }

    /**
     * This function allows the user to adda location to tasks.
     *
     * @param taskWithLocation is of String type which contains the desired date of
     *                  schedule.
     * @return sortDateList the sorted schedule of all the tasks on a particular date.
     */
    public Task addLocation(Integer indexOfTask, String taskWithLocation) {
        Task taskHasLocation = listOfTasks.get(indexOfTask);
        taskHasLocation.hasLocation = true;
        taskHasLocation.comment += ("Location of the task is " + taskWithLocation);
        // This is to later encompass further key information such as eg. exam is at location
        // taskHasLocation.comment += (" the" + taskHasLocation.getClass().toString()
        // .replace("class task.", " ") + " task is at " + taskWithLocation);
        return taskHasLocation;
    }

    /**
     * This function allows the user to obtain the tasks on a particular date.
     *
     * @param dayToFind is of String type which contains the desired date of
     *                  schedule.
     * @return sortDateList the sorted schedule of all the tasks on a particular date.
     */
    public ArrayList<Task> schedule(String dayToFind) {
        ArrayList<Task> sortedDateList = new ArrayList<Task>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).toString().contains(dayToFind)) {
                sortedDateList.add(listOfTasks.get(i));
            }
        }
        Collections.sort(sortedDateList, DateComparator);
        return sortedDateList;
    }

    /**
     * This function allows the user to obtain the tasks on a particular date sorted by priority.
     *
     * @param dayToFind is of String type which contains the desired date of
     *                  schedule.
     * @return priorityList the tasks of the given day sorted by priority
     */
    public ArrayList<Task> obtainPriorityList(String dayToFind) {
        ArrayList<Task> priorityList = new ArrayList<Task>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).toString().contains(dayToFind) && !listOfTasks.get(i).priority.equals(Priority.LOW)
                && !listOfTasks.get(i).priority.equals(Priority.INVALID)) {
                priorityList.add(listOfTasks.get(i));
            }
        }
        Collections.sort(priorityList, PriorityComparator);
        return priorityList;
    }

    /**
     * This function allows the user to obtain the tasks on a particular date, but only with description.
     *
     * @param dayToFind is of String type which contains the desired date of
     *                  schedule.
     * @return sortDateList which contains only the descriptions of the tasks.
     */
    public ArrayList<String> scheduleForDay(String dayToFind) {
        ArrayList<Task> obtainDescriptions = schedule(dayToFind);
        ArrayList<String> scheduleDescriptionOnly = new ArrayList<>();
        for (int i = 0; i < obtainDescriptions.size(); i++) {
            if (obtainDescriptions.get(i).toString().contains(dayToFind)) {
                scheduleDescriptionOnly.add(obtainDescriptions.get(i).description.split("\\d+",2)[0].trim());
            }
        }
        return scheduleDescriptionOnly;
    }

    /**
     * This function allows the user to edit the task description.
     *
     * @param indexOfTask    Location of task in the list
     * @param newDescription The new task description to be updated
     * @return taskToBeEdited The task that had its description edited
     */
    public Task editTaskDescription(int indexOfTask, String newDescription) {
        Task taskToBeEdited = listOfTasks.get(indexOfTask);
        taskToBeEdited.description = newDescription;
        return taskToBeEdited;
    }

    /**
     * Function to allow user to edit/add comments to existing tasks.
     *
     * @param indexOfTask Index of task in list
     * @param comment     commnent to be added/edited
     * @return taskToBeEdited The task that has its comment edited/added
     */
    public Task editTaskComment(int indexOfTask, String comment) {
        Task taskToBeEdited = listOfTasks.get(indexOfTask);
        taskToBeEdited.comment = comment;
        return taskToBeEdited;
    }

    public ArrayList<Task> getTasks() {
        return listOfTasks;
    }

    public int getSize() {
        return listOfTasks.size();
    }

}