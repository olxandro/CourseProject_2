package repeatable;

import dairy.Task;

import java.time.LocalDate;

public class Weekly implements Repeatable{
    @Override
    public boolean getNextDate(LocalDate date, Task task) {
        return (date.getDayOfWeek().getValue() == task.getCreationDate().getDayOfWeek().getValue() &&
                task.getCreationDate().isBefore(date)) ||
                task.getCreationDate().equals(date);
    }

    @Override
    public String toString() {
        return "Еженедельно";
    }
}
