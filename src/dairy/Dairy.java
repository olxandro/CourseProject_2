package dairy;

import exceptions.NoTaskException;

import java.time.LocalDate;
import java.util.*;

public class Dairy {
    private final static HashMap<Integer, Task> dairy = new HashMap<>();
    private final static HashMap<Integer, Task> deletedTasks = new HashMap<>();
    public void addTask(Task task) throws NoTaskException {
        if (task != null) {
            dairy.put(task.getID(), task);
        } else {
            throw new NoTaskException("Переданная задача пуста!");
        }
    }
    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> task :
                dairy.entrySet()) {
            if (task.getValue().getRepeatable().getNextDate(date, task.getValue())) {
                taskList.add(task.getValue());
            }
        }
        return taskList;
    }
    public Map<LocalDate, List<Task>> getGroupedTasks(LocalDate date) {
        Map<LocalDate, List<Task>> groupedTasks = new LinkedHashMap<>();
        LocalDate day = LocalDate.now();
        while (day.getDayOfYear() == date.getDayOfYear()) {
            List<Task> tasksOnDay = new ArrayList<>();
            groupedTasks.put(day, tasksOnDay);
            day = day.plusDays(1);
        }
        return groupedTasks;
    }
    public void deleteTask(int id) throws NoTaskException {
        Task task = dairy.remove(id);
        if (task != null) {
            deletedTasks.put(task.getID(), task);
        } else {
            throw new NoTaskException("Такой задачи нет в ежедневнике!");
        }
    }
    public static HashMap<Integer, Task> getDairy() {
        return dairy;
    }
    public static HashMap<Integer, Task> getDeletedTasks() {
        return deletedTasks;
    }
    public static boolean isTaskExist(int id) {
        return dairy.containsKey(id);
    }
    public void setTaskName(int id, String newName) {
        dairy.get(id).setName(newName);
    }
    public void setTaskDescription(int id, String newDescription) {
        dairy.get(id).setDescribe(newDescription);
    }
}