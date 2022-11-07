import java.time.LocalDate;

public enum Repeatable {

    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONHLY("ежемесячная"),
    YEARLY("ежедневно"),
    ONCE("ежегодная"),

    ;
    private String message;

    Repeatable(String message) {
        this.message = message;
    }

    public LocalDate getNextTime(LocalDate createTimeDate) {
        switch (this) {
            case DAILY:
                return createTimeDate.plusDays(1);
            case WEEKLY:
                return createTimeDate.plusWeeks(1);
            case MONHLY:
                return createTimeDate.plusMonths(1);
            case YEARLY:
                return createTimeDate.plusYears(1);
        }
        return null;
    }

    public String getMessage() {
        return message;
    }
}
