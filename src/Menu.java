import NotebookTask.RepeatTask;
import NotebookTask.TaskExeption;
import NotebookTask.TypeTask;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private Planner planner = new Planner();

    public void menushkaRun() {
        try (
                Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            try {
                                planner.addTaskNotbook(inputTask(), addTextTask(), addTypeTask(), addRepeatTask(), addDateTime());
                            } catch (TaskExeption | NullPointerException | DateTimeException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case 2:
                            try {
                                System.out.println("Введите номер задача для удаления: ");
                                planner.deleteTask(scanner.nextInt());
                            } catch (RuntimeException e) {
                                throw new RuntimeException("Введите корректно данные!", e);
                            }
                            break;
                        case 3:
                            try {
                                System.out.println("Загружаем список задач...");
                                LocalDate localDate = addDate();
                                if (localDate != null) {
                                    System.out.println("Задачи на день: " + localDate);
                                    planner.takeTaskDay(localDate);
                                }
                            } catch (DateTimeException e) {
                                throw new DateTimeException("введите правильно!");
                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }

    private static String inputTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название задачи: ");
        return scanner.nextLine();
    }

    private static String addTextTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите описание задачи: ");
        return scanner.nextLine();
    }

    private static int inputTypeTask() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Введите тип задачи: 
                0 - рабочая
                1 - личная
                """);
        int number;
        do {
            number = scanner.nextInt();
        }
        while (number > 2 & number < 0);

        return number;
    }

    private static TypeTask addTypeTask() {
        return switch (inputTypeTask()) {
            case 0 -> TypeTask.typeWork;
            case 1 -> TypeTask.typePersonal;
            default ->
                    throw new IllegalStateException("Упс.. Такой команды не существует. ВВедите цифру 0 или 1 " + inputTypeTask());
        };
    }

    private static int inputRepeatTask() {
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                Введите повторяемость задачи: 
                0 - Одиночная
                1 - Ежедневная
                2 - Еженедельная
                3 - Ежемесячная
                4 - Ежегодная 
                """);

        if (!scanner.hasNextInt()) {
            System.out.println("Извините, но это явно не число... Попробуйте снова :)");
        } else if (number < 0 & number > 4) {
            System.out.println("Упс.. Такой команды не существует. ВВедите цифру 0 или 4");
        } else {
            number = scanner.nextInt();
        }
        return number;
    }

    private static RepeatTask addRepeatTask() {
        return switch (inputRepeatTask()) {
            case 0 -> RepeatTask.ONCE;
            case 1 -> RepeatTask.EVERYDAY;
            case 2 -> RepeatTask.EVERYWEEK;
            case 3 -> RepeatTask.EVERYMONTH;
            case 4 -> RepeatTask.EVERYEAR;
            default ->
                    throw new IllegalStateException("Упс.. Такой команды не существует. ВВедите цифру от 0 до 4 " + inputRepeatTask());
        };
    }

    private static LocalDateTime addDateTime() throws DateTimeException, NullPointerException {
        Scanner scanner = new Scanner(System.in);
        return LocalDateTime.of(addDate(), addTime());
    }

    private static LocalDate addDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Введите дату в формате yyyy.MM.dd: """);
        return LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }


    private static LocalTime addTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Введите время в формате HH.mm: """);
        return LocalTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("HH.mm"));
    }
}
