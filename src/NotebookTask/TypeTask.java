package NotebookTask;

public enum TypeTask {
    typeWork("Рабочая задача"), typePersonal("Личная задача");
    private final String type;

    TypeTask(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Tип задачи: " + type;
    }
}
