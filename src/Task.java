import java.time.LocalDate;

public class Task {

    private String name;
    private String description;
    private TypeTasks type;
    private LocalDate createTimeDate;
    private Repeatable repeatable;
    private int id;
    private static int counter;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TypeTasks getType() {
        return type;
    }

    public LocalDate getCreateTimeDate() {
        return createTimeDate;
    }

    public Repeatable getRepeatable() {
        return repeatable;
    }

    public int getId() {
        return id;
    }

    public Task(String name, TypeTasks type) throws NoNameException, NoTypeException {
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            this.name = name;
        } else {
            throw new NoNameException("Не указан заголовок задачи!");
        }
        this.description = "";
        if (type != null) {
            this.type = type;
        } else {
            throw new NoTypeException("Не указан тип задачи!");
        }
        this.createTimeDate = LocalDate.now();
        this.id = counter + 1;
        this.repeatable = Repeatable.ONCE;
    }

    public Task(String name, String description, TypeTasks type) throws NoTypeException, NoNameException, NoDescException {
        new Task(name, type);
        if (description != null && !description.isEmpty() && description.isBlank()) {
            this.description = description;
        } else {
            throw new NoDescException("Отсутствует описание задачи!");
        }
    }

    public Task(String name, String description, TypeTasks type, LocalDate createTimeDate, Repeatable repeatable, int id) throws NoDescException, NoTypeException, NoNameException, NoRepeatException {
        new Task(name, description, type);
        if (repeatable != null) {
            this.repeatable = repeatable;
        } else {
            throw new NoRepeatException("Отсутствует признак повторяемости задачи!");
        }
    }

    public LocalDate getNextTime() {
        return repeatable.getNextTime(createTimeDate);
    }
}
