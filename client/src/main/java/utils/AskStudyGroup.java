package utils;

import data.*;
import exceptions.NullValueException;
import exceptions.NumberOutOfBoundsException;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class AskStudyGroup {

    IOManager ioManager;

    public AskStudyGroup(IOManager ioManager){
        this.ioManager = ioManager;
    }

    public StudyGroup getStudyGroupFromUser() throws NumberOutOfBoundsException, NullValueException, IOException {
        String name = asker(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите имя группы (String)",
                "Имя не может быть пустым",
                false);

        Long coordinateX = asker(Long::valueOf,
                arg -> (arg <= 722),
                "Введите координату X (Long)",
                "Значение должно быть < 723",
                false);
        Long coordinateY = asker(Long::valueOf,
                arg -> (arg <= 102),
                "Введите координату Y (Long)",
                "Значение должно быть < 103",
                false);
        Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
        Long studentsCount = asker(Long::valueOf,
                arg -> (arg > 0),
                "Введите количество студентов (Long)",
                "Значение должно быть > 0",
                false);
        Integer expelledStudents = asker(Integer::valueOf,
                arg -> (arg > 0),
                "Введите количество отчисленных студентов (Integer)",
                "Значение должно быть > 0",
                true);
        int shouldBeExpelled = asker(Integer::valueOf,
                arg -> (arg > 0),
                "Введите количество студентов, представленных к отчислению (int)",
                "Значение должно быть > 0",
                false);
        Semester semester = asker(Semester::valueOf,
                arg -> true,
                "Введите семестр\nДоступные варианты:\n" + Semester.stringOfValues(),
                "",
                true);
        Person admin = getGroupAdmin();
        return new StudyGroup(name, coordinates, studentsCount, expelledStudents, shouldBeExpelled, semester, admin);

    }

    private Person getGroupAdmin() throws IOException, NullValueException, NumberOutOfBoundsException {
        String name = asker(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите имя админа (String)",
                "Имя не может быть пустым",
                false);
        Long height = asker(Long::valueOf,
                arg -> (arg > 0),
                "Введите рост админа (Long)",
                "Значение должно быть > 0",
                true);
        Color hairColor = asker(Color::valueOf,
                arg -> true,
                "Введите цвет волос админа\nДоступные варианты:\n" + Color.stringOfValues(),
                "",
                false);
        Country nationality = asker(Country::valueOf,
                arg -> true,
                "Введите национальность админа\nДоступные варианты\n" + Country.stringOfValues(),
                "",
                true);
        Location location = getLocation();
        return new Person(name, height, hairColor, nationality, location);
    }

    private Location getLocation() throws NullValueException, IOException {
        Double locationX = asker(Double::valueOf,
                arg -> true,
                "Введите локацию админа X (Double)",
                "",
                false);
        Double locationY = asker(Double::valueOf,
                arg -> true,
                "Введите локацию админа Y (Double)",
                "",
                false);

        double locationZ = asker(Double::valueOf,
                arg -> true,
                "Введите локацию админа Z (double)",
                "",
                false);

        return new Location(locationX, locationY, locationZ);
    }


    public <T> T asker(Function<String, T> function,
                       Predicate<T> predicate,
                       String message,
                       String negativeResponse,
                       boolean nullable) throws IOException {

        T value = null;
        while (true){
            ioManager.println(message);
            ioManager.prompt();
            try {
                String input = ioManager.readLine();
                if (input.equals("") && nullable) {
                    return null;
                }
                value = function.apply(input);
                if (predicate.test(value)){
                    return value;
                }
                else {
                    ioManager.printerr(negativeResponse);
                }
            }catch (IllegalArgumentException e){
                ioManager.printerr("Значение неверного формата");
            }
            catch (NullPointerException e){
                ioManager.printerr("Значение не может быть null");
            }
        }

    }
}
