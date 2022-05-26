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
    Asker asker;

    public AskStudyGroup(IOManager ioManager){
        this.ioManager = ioManager;
        asker = new Asker(ioManager);
    }

    public StudyGroup getStudyGroupFromUser() throws NumberOutOfBoundsException, NullValueException, IOException {
        String name = asker.ask(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите имя группы (String)",
                "Имя не может быть пустым",
                false);

        Long coordinateX = asker.ask(Long::valueOf,
                arg -> (arg <= 722),
                "Введите координату X (Long)",
                "Значение должно быть < 723",
                false);
        Long coordinateY = asker.ask(Long::valueOf,
                arg -> (arg <= 102),
                "Введите координату Y (Long)",
                "Значение должно быть < 103",
                false);
        Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
        Long studentsCount = asker.ask(Long::valueOf,
                arg -> (arg > 0),
                "Введите количество студентов (Long)",
                "Значение должно быть > 0",
                false);
        Integer expelledStudents = asker.ask(Integer::valueOf,
                arg -> (arg > 0),
                "Введите количество отчисленных студентов (Integer)",
                "Значение должно быть > 0",
                true);
        int shouldBeExpelled = asker.ask(Integer::valueOf,
                arg -> (arg > 0),
                "Введите количество студентов, представленных к отчислению (int)",
                "Значение должно быть > 0",
                false);
        Semester semester = asker.ask(Semester::valueOf,
                arg -> true,
                "Введите семестр\nДоступные варианты:\n" + Semester.stringOfValues(),
                "",
                true);
        Person admin = getGroupAdmin();
        return new StudyGroup(name, coordinates, studentsCount, expelledStudents, shouldBeExpelled, semester, admin);

    }

    private Person getGroupAdmin() throws IOException, NullValueException, NumberOutOfBoundsException {
        String name = asker.ask(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите имя админа (String)",
                "Имя не может быть пустым",
                false);
        Long height = asker.ask(Long::valueOf,
                arg -> (arg > 0),
                "Введите рост админа (Long)",
                "Значение должно быть > 0",
                true);
        Color hairColor = asker.ask(Color::valueOf,
                arg -> true,
                "Введите цвет волос админа\nДоступные варианты:\n" + Color.stringOfValues(),
                "",
                false);
        Country nationality = asker.ask(Country::valueOf,
                arg -> true,
                "Введите национальность админа\nДоступные варианты\n" + Country.stringOfValues(),
                "",
                true);
        Location location = getLocation();
        return new Person(name, height, hairColor, nationality, location);
    }

    private Location getLocation() throws NullValueException, IOException {
        Double locationX = asker.ask(Double::valueOf,
                arg -> true,
                "Введите локацию админа X (Double)",
                "",
                false);
        Double locationY = asker.ask(Double::valueOf,
                arg -> true,
                "Введите локацию админа Y (Double)",
                "",
                false);

        double locationZ = asker.ask(Double::valueOf,
                arg -> true,
                "Введите локацию админа Z (double)",
                "",
                false);

        return new Location(locationX, locationY, locationZ);
    }
}
