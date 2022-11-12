package repeatable;

import dairy.Task;

import java.time.LocalDate;

public class Monthly implements Repeatable {
    @Override
    public boolean getNextDate(LocalDate date, Task task) {
        return (date.getDayOfMonth() == task.getCreationDate().getDayOfMonth() &&
                task.getCreationDate().isBefore(date)) ||
                task.getCreationDate().equals(date);
    }

    @Override
    public String toString() {
        return "Ежемесячно";
    }
}
