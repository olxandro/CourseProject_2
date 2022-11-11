import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private TypeTasks type;
    private LocalDateTime createTimeDate;
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

    public LocalDateTime getCreateTimeDate() {
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
        this.createTimeDate = LocalDate.now().atStartOfDay();
        this.id = counter + 1;
        this.repeatable = Repeatable.ONCE;
        System.out.println("Задача " + getName() + " создана!");
    }

    public Task(String name, String description, TypeTasks type) throws NoTypeException, NoNameException, NoDescException {
        new Task(name, type);
        if (description != null && !description.isEmpty() && !description.isBlank()) {
            this.description = description;
        } else {
            throw new NoDescException("Отсутствует описание задачи!");
        }
    }

    public Task(String name, String description, TypeTasks type, Repeatable repeatable, LocalDateTime createDateTime) throws NoDescException, NoTypeException, NoNameException, NoRepeatException {
        new Task(name, description, type);
        if (repeatable != null) {
            this.repeatable = repeatable;
        } else {
            throw new NoRepeatException("Отсутствует признак повторяемости задачи!");
        }
        this.createTimeDate = createDateTime;
    }

    public LocalDate getNextTime() {
        return repeatable.getNextTime(LocalDate.from(createTimeDate));
    }

    @Override
    public String toString() {
        return "Задача:" +
                "заголовок - " + name +
                ", описание - " + description +
                ", тип - " + type +
                ", дата создания - " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(createTimeDate) +
                ", вид повторения - " + repeatable +
                ", id - " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                type == task.type && Objects.equals(createTimeDate, task.createTimeDate) &&
                repeatable == task.repeatable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, type, createTimeDate, repeatable, id);
    }
}
