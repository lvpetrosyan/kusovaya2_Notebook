package NotebookTask;

public class TaskExeption extends Exception {
    Notebook notebook;

    public TaskExeption(String message) {
        super(message);
        this.notebook = notebook;
    }

    public static class Examination {
        public String s;

        public static String exam(String s) throws TaskExeption {
            if (s.isEmpty() || s.isBlank() || s == null) {
                throw new TaskExeption("Введите корретно данные!");
            } else {
                return s;
            }
        }
    }
}
