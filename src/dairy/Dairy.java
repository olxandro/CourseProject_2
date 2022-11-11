package dairy;

import exceptions.NoTaskException;

import java.time.LocalDate;
import java.util.*;

public class Dairy {
    private final static HashMap<Integer, Task> dairy = new HashMap<>();
    private final static HashMap<Integer, Task> deletedTasks = new HashMap<>(); //удаленные задачи
    public void addTask(Task task) throws NoTaskException {
        if (task != null) {
            dairy.put(task.getID(), task);
        } else {
            throw new NoTaskException("Вы пытаетесь передать пустую задачу!");
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
}