import NotebookTask.Notebook;
import NotebookTask.RepeatTask;
import NotebookTask.TaskExeption;
import NotebookTask.TypeTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Planner {
    private List<Notebook> planner = new ArrayList<>();
    private List<Notebook> database = new ArrayList<>();

    public List<Notebook> getPlanner() {
        return planner;
    }

    public List<Notebook> getDatabase() {
        return database;
    }

    public void addTaskNotbook(String nameTask, String textTask, TypeTask typeTask, RepeatTask REPEATTASK, LocalDateTime firstDate) throws TaskExeption {
        planner.add(new Notebook(nameTask, textTask, typeTask, REPEATTASK, firstDate));
        System.out.println("Задача добавлена № " + Notebook.getId());
    }

    public void deleteTask(int id) {
        for (Notebook notebook : planner) {
            if (notebook.getId() == id) {
                database.add(notebook);
            }
        }
        if (planner.removeIf(notebook -> notebook.getId() == id)) {
            System.out.println("Задача удалена № " + id);
        } else {
            System.out.println("Такой задачи не существует!");
        }
    }

    public void takeTaskDay(LocalDate localdate) {
        boolean isTask = false;
        for (Notebook notebook : planner) {
            if (notebook.repeatDate(localdate)) {
                System.out.println(notebook);
                isTask = true;
            }
        }
        if (!isTask) {
            System.out.println("задач нет");
        }
    }
}
