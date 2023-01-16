package NotebookTask;

public enum RepeatTask {
    ONCE("Единожды "), EVERYDAY("Каждый день"), EVERYWEEK("Каждую неделю"), EVERYMONTH("Ежемесячно"), EVERYEAR("Ежегодично");
    private final String REPEATTASK;

    RepeatTask(String REPEATTASK) {
        this.REPEATTASK = REPEATTASK;
    }

    public String getREPEATTASK() {
        return REPEATTASK;
    }

    @Override
    public String toString() {
        return " " + REPEATTASK;
    }
}
