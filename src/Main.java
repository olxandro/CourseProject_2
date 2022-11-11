import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Введите соответствующую цифру пункта меню: ");
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        inputTask(scanner);
                        break;
                    case 2:
                        deleteTask(scanner);
                        break;
                    case 3:
                        getTaskOnDay(scanner);
                        break;
                    case 0:
                        System.exit(0);
                }
            } else {
                scanner.next();
                System.out.println("Введите соответствующую цифру пункта меню: ");
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                "1 - добавить задачу\n" +
                        "2 - удалить задачу\n" +
                        "3 - вывести список задач за указанную дату\n" +
                        "0 - выход из меню"
        );
    }

    private static void inputTask(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите название задачи: ");
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи: ");
        String taskDescribe = scanner.nextLine();
        System.out.println("Введите дату задачи в формате dd/mm/yyyy: ");
        String date = scanner.nextLine();
        LocalDate taskDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Введите время задачи в формате HH:mm: ");
        String time = scanner.nextLine();
        LocalTime taskTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime taskDateTime = LocalDateTime.of(taskDate, taskTime);
        TypeTasks typeTasks = getTypeOfTask(scanner);
        Repeatable repeatable = getRepeatable(scanner);
        try {
            Dairy dairy = new Dairy();
            dairy.addTask(new Task(taskName, taskDescribe, typeTasks, repeatable, taskDateTime));
        } catch (NoNameException | NoDescException | NoTypeException | NoRepeatException | NoTaskException e) {
            System.out.println(e);
        }
    }

    private static TypeTasks getTypeOfTask(Scanner scanner) {
        TypeTasks typeTasks;
        while (true) {
            System.out.println("Введите категорию задачи: \n" +
                    "0 - личная,\n" +
                    "1 - рабочая.\n");
            if (scanner.hasNextInt()) {
                int a = scanner.nextInt();
                if (a == 0) {
                    return TypeTasks.PERSONAL;
                } else if (a == 1) {
                    return TypeTasks.WORK;
                } else {
                    System.out.println("Введено число не из списка. Повторите попытку!");
                }
            } else {
                System.out.println("Введено не число. Повторите попытку!");
            }
        }
    }

    private static Repeatable getRepeatable(Scanner scanner) {
        Repeatable repeatable;
        while (true) {
            System.out.println("Введите повторяемость задачи:\n" +
                    "0 -  однократная,\n" +
                    "1 -  ежедневная,\n" +
                    "2 -  еженедельная,\n" +
                    "3 -  ежемесячная,\n" +
                    "4 -  ежегодная,\n");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 0:
                        return Repeatable.ONCE;
                    case 1:
                        return Repeatable.DAILY;
                    case 2:
                        return Repeatable.WEEKLY;
                    case 3:
                        return Repeatable.MONTHLY;
                    case 4:
                        return Repeatable.YEARLY;
                    default:
                        System.out.println("Введено число не из списка. Повторите попытку!");
                }
            } else {
                System.out.println("Введено не число. Повторите попытку!");
            }
        }

    }

    private static void getTaskOnDay(Scanner scanner) {
        while (true) {
            scanner.nextLine();
            System.out.println("Введите дату в формате dd/mm/yyyy : ");
            Dairy dairy = new Dairy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateS = scanner.nextLine();
            LocalDate date;
            try {
                date = LocalDate.parse(dateS, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат ввода даты.");
                continue;
            }
            List<Task> taskOnDate = dairy.getTasksOnDate(date);
            for (Task task : taskOnDate) {
                System.out.println(task);
            }
            break;
        }
    }

    private static void deleteTask(Scanner scanner) {
        while (true) {
            System.out.println("Введите id задачи для удаления: \n" +
                    "0 - выйти в предыдущее меню");
            int taskId;
            try {
                taskId = scanner.nextInt();
                if (taskId == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Вы ввели не число, попробуйте снова!");
                continue;
            }
            Dairy dairy = new Dairy();
            try {
                dairy.deleteTask(taskId);
            } catch (NoTaskException e) {
                System.out.println(e);
            }
        }
    }
}