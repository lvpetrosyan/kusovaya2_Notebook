package NotebookTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Notebook {
    private String nameTask;
    private String textTask;
    private TypeTask typeTask;
    private LocalDateTime firstDate;
    private static LocalDateTime dateOfCreation;
    private static int id;
    private static int counter = 1;
    private RepeatTask REPEATTASK;


    public Notebook(String nameTask, String textTask, TypeTask typeTask, RepeatTask REPEATTASK, LocalDateTime firstDate) throws TaskExeption {
        this.nameTask = TaskExeption.Examination.exam(nameTask);
        this.textTask = TaskExeption.Examination.exam(textTask);
        this.typeTask = typeTask;
        this.REPEATTASK = REPEATTASK;
        this.firstDate = firstDate;
        id = counter++;
        dateOfCreation = LocalDateTime.now();
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getTextTask() {
        return textTask;
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public RepeatTask REPEATTASK() {
        return REPEATTASK;
    }

    public LocalDate getFirstDate() {
        return firstDate.toLocalDate();
    }

    public static int getId() {
        return id;
    }

    public RepeatTask getREPEATTASK() {
        return REPEATTASK;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setTextTask(String textTask) {
        this.textTask = textTask;
    }

    private LocalDate addNextDate(LocalDate localDate) {
        LocalDate localDate1 = null;
        switch (REPEATTASK) {
            case EVERYDAY -> localDate1 = localDate.plusDays(1L);
            case EVERYEAR -> localDate1 = localDate.plusYears(1L);
            case EVERYWEEK -> localDate1 = localDate.plusWeeks(1L);
            case EVERYMONTH -> localDate1 = localDate.plusMonths(1L);
        }
        return localDate1;
    }

    private long changeDateNumber(LocalDate localDate) {
        return switch (REPEATTASK) {
            case ONCE -> 0L;
            case EVERYDAY -> ChronoUnit.DAYS.between(getFirstDate(), localDate);
            case EVERYEAR -> ChronoUnit.YEARS.between(getFirstDate(), localDate);
            case EVERYWEEK -> ChronoUnit.WEEKS.between(getFirstDate(), localDate);
            case EVERYMONTH -> ChronoUnit.MONTHS.between(getFirstDate(), localDate);
        };
    }

    public boolean repeatDate(LocalDate localDate) {
        long date = changeDateNumber(localDate);
        LocalDate dateFirst = getFirstDate();
        boolean repeatDate = false;
        for (int i = 0; i <= date; i++) {
            if (dateFirst.equals(localDate)) {
                repeatDate = true;
                break;
            } else {
                dateFirst = addNextDate(dateFirst);
            }
        }
        return repeatDate;
    }


    @Override
    public String toString() {
        return String.format("\nЗадача №%d. %10s\nОписание: %s\n", id, nameTask, textTask + "\nДата выполнения задачи: "
                + firstDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + "\n" + typeTask + "\nПовторяемость: "
                + getREPEATTASK() + "\nСоздана: " + dateOfCreation.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notebook notebook = (Notebook) o;
        return Objects.equals(nameTask, notebook.nameTask) && Objects.equals(textTask, notebook.textTask)
                && typeTask == notebook.typeTask && Objects.equals(firstDate, notebook.firstDate) && REPEATTASK == notebook.REPEATTASK;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTask, textTask, typeTask, firstDate, REPEATTASK);
    }
}
