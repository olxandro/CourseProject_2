package dairy;

public enum TypeOfTask {

    PERSONAL("Личная задача"),
    WORK("Рабочая задача");
    private final String message;

    TypeOfTask(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
