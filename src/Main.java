import dairy.Dairy;
import dairy.Task;
import dairy.TypeOfTask;
import exceptions.*;
import repeatable.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private final static Dairy dairy = new Dairy();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Введите соответствующую цифру пункта меню:");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        inputTask(sc);
                        break;
                    case 2:
                        deleteTask(sc);
                        break;
                    case 3:
                        getTasksOnDay(sc);
                        break;
                    case 4:
                        printAllTasks();
                        break;
                    case 0:
                        System.exit(0);
                }
            } else {
                sc.next();
                System.out.println("Выберите пожалуйста пункт меню из списка!");
            }
        }
    }


    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу,\n" +
                        "2. Удалить задачу,\n" +
                        "3. Получить задачи на указанный день,\n" +
                        "4. Распечатать список всех задач,\n" +
                        "0. Выход."
        );
    }

    /**
     * метод добавления задачи
     * @param scanner - вводимые данные
     */
    private static void inputTask (Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название задачи:");
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String taskDescribe = scanner.nextLine();
        TypeOfTask type = getTypeOfTask(scanner);
        Repeatable repeatable = getRepeatable(scanner);
        System.out.println("Введите первую дату задачи");
        LocalDate date = getDate(scanner);
        try {
            Task task = new Task(taskName, taskDescribe, type, repeatable, date);
            dairy.addTask(task);
            System.out.println("Задача добавлена в ежедневник!");
        } catch (NoNameException | NoTypeException | NoDescException | NoRepeatException | NoTaskException e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
    private static TypeOfTask getTypeOfTask(Scanner scanner) {
        while (true) {
            System.out.println("Введите категорию задачи:\n" +
                    "0. личная,\n" +
                    "1. рабочая.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i == 0) {
                    return TypeOfTask.PERSONAL;
                } else if (i == 1) {
                    return TypeOfTask.WORK;
                } else {
                    System.out.println("Введите пожалуйста число из списка!");
                }
            } else {
                System.out.println("Введено не число. Повторите попытку!");
            }
        }
    }

    private static repeatable.Repeatable getRepeatable(Scanner scanner) {
        while (true) {
            System.out.println("Введите повторяемость задачи:\n" +
                    "0. однократная,\n" +
                    "1. ежедневная,\n" +
                    "2. еженедельная,\n" +
                    "3. ежемесячная,\n" +
                    "4. ежегодная.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 0:
                    return new Once();
                    case 1:
                    return new Daily();
                    case 2:
                    return new Weekly();
                    case 3:
                    return new Monthly();
                    case 4:
                    return new Yearly();
                    default:
                        System.out.println("Введите пожалуйста число из списка!");
                }
            } else {
                System.out.println("Введено не число. Повторите попытку!");
            }
        }
    }

    /**
     * метод получения задачи на день
     * @param scanner - вводимая дата
     */
    private static void getTasksOnDay(Scanner scanner) {
            List<Task> tasksOnDate = dairy.getTasksOnDate(getDate(scanner));
            for (Task task : tasksOnDate) {
                System.out.println(task);
        }
    }

    /**
     * метод распечатки всех задач
     */
    private static void printAllTasks() {
        HashMap<Integer, Task> dairy = Dairy.getDairy();
        for (Map.Entry<Integer, Task> task :
                dairy.entrySet()) {
            System.out.println(task.getValue());
        }
    }

    /**
     * метод удаления задачи по id
     * @param scanner - вводимые данные
     */
    private static void deleteTask(Scanner scanner) {
        while (true) {
            System.out.println("Введите ID задачи для удаления: \n" +
                    "0. выйти в предыдущее меню");
            int taskID;
            try {
                taskID = scanner.nextInt();
                if (taskID == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("введено не число!");
                continue;
            }
            try {
                dairy.deleteTask(taskID);
            } catch (NoTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Метод ввода даты для задачи
     * @param scanner - ввод строки в формате даты
     * @return возвращает распарсенную дату
     */
    private static LocalDate getDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("введите дату в формате dd/mm/yyyy:");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(scanner.next(), formatter);
            } catch (Exception e) {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}