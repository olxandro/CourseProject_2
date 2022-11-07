import java.time.LocalDate;
import java.util.*;

public class Dairy {

    private static HashMap<Integer, Task> dairy = new HashMap<>();
    private static HashMap<Integer, Task> deletedTasks = new HashMap<>();

    public void addTask(Task task) throws NoTaskException {
        if (task != null) {
            dairy.put(task.getId(), task);
        } else {
            throw new NoTaskException("Задача - пустышка!");
        }
    }

    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> task : dairy.entrySet()) {
            LocalDate localDate = task.getValue().getCreateTimeDate();
            if (localDate.getDayOfWeek().getValue() == date.getDayOfWeek().getValue() ||
                    localDate.getDayOfMonth() == date.getDayOfMonth() ||
                    localDate.getDayOfYear() == date.getDayOfYear() ||
                    task.getValue().getRepeatable().equals(Repeatable.DAILY)) {
                taskList.add(task.getValue());
            }
        }
        return taskList;
    }

    public void deleteTask(int id) throws NoTaskException {
        Task task = dairy.remove(id);
        if (task != null) {
            deletedTasks.put(task.getId(), task);
        } else {
            throw new NoTaskException("Задачи не существует!");
        }
    }

    public static HashMap<Integer, Task> getDairy() {
        return dairy;
    }
}
