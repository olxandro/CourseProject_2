import java.time.LocalDate;

public enum Repeatable {

    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    YEARLY("ежегодная"),
    ONCE("однократная"),

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
            case MONTHLY:
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
