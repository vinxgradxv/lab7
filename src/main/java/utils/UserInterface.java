package utils;

import commands.Command;
import commands.CommandManger;
import data.*;
import exceptions.*;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс, который реализует пользовательский интерфейс
 */
public class UserInterface {
    /**
     * Режим чтения скрипта
     */
    private static boolean isScriptMode = false;
    /**
     * Символ, появляющийся, когда от пользователя ожидается введение команды
     */
    private static final String waitForCommand = "$";

    /**
     * Метод, реализующий интерактивный режим
     */
    public static void interactiveMode() {
        CommandManger.setCommands();
        String input = "";
        Command command = null;
        while (!input.equals("exit")) {
            try {
                Scanner console = new Scanner(System.in);
                System.out.print(waitForCommand);
                input = console.nextLine();
                command = getCommandFromInput(input);
                if(command.getParametersCount()+1 != input.split(" ").length){
                    throw new WrongNumberOfParametersException();
                }
                command.execute(input, console);
            }
            catch (WrongAmountOfCoordinatesException e){
                System.out.println("Неверное количество координат");
            }
            catch (WrongNumberOfParametersException e){
                System.out.println("Неверное количество параметров у команды, должно быть - " + command.getParametersCount() + " ( " + command.getParameters()+")");
            }
            catch (CommandNotFoundException e){
                System.out.println("Такой команды нет");
            }
            catch (NumberOutOfBoundsException e){
                System.out.println("Параметр не вписывается в установленные рамки");
            }
            catch (IllegalArgumentException e){
                System.out.println("Не верный формат параметра, должно быть " + command.getParameters());
            }

        }
    }

    /**
     * Метод, возвращающий команду
     * @param input строка, введенная пользователем
     * @return command
     */
    public static Command getCommandFromInput(String input) throws CommandNotFoundException {
        String[] values = input.split(" ");
        List<Command> commands = CommandManger.getCommands();
        for (Command command:commands) {
            if(command.getName().equals(values[0])){
                return command;
            }
        }
        throw new CommandNotFoundException();
    }

    /**
     * Метод, реализующий режим чтения скрипта
     * @param path путь к файлу
     * @param scanner обертка для потока ввода
     */
    public static void scriptMode(String path, Scanner scanner){
        int line = 0;
        isScriptMode = true;
        Scanner reader;
        try {
            File file = new File(path);
            boolean examination = isRecursive(file);
            if (examination) {
                System.out.println("При исполнении скрипта возможно переполнение стека из-за рекурсивных вызовов");
                System.out.println("Скрипт исполнен не будет");
            }
            if (!examination) {
                reader = new Scanner(file);
                while (reader.hasNext()) {
                    String input = reader.nextLine();
                    line += 1;
                    Command command = getCommandFromInput(input);
                    if (command.getParametersCount() + 1 != input.split(" ").length) {
                        throw new WrongNumberOfParametersException();
                    }
                    System.out.println("Команда " + line + ": ");
                    command.execute(input, reader);

                }
            }

        }
        catch (WrongAmountOfCoordinatesException e){
            System.out.println("Неверное количество координат в строке " + line);
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }
        catch (WrongNumberOfParametersException e){
            System.out.println("Неверное количество параметров в строке " + line);
        }
        catch (NumberOutOfBoundsException e){
            System.out.println("Параметр, не вписывающийся в установленные рамки, в строке " + line);
        }
        catch (CommandNotFoundException e){
            System.out.println("Такой команды не существует в строке " + line);
        }
        catch (IllegalArgumentException e){
            System.out.println("Неверные формат параметра у одной из команд в строке " + line);
        }
        catch (NoSuchElementException e){
            System.out.println("Неверное количество значений полей элемента при описании команды " + line);
        }
        catch (StackOverflowError e){
            System.out.println("При выполнении скрипта, произошли рекурсивные вызовы и случилось переполнение стека");
        }

        finally {
            isScriptMode = false;
        }

    }

    private static boolean isRecursive(File file){
        int line = 0;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                String input = reader.nextLine();
                line += 1;
                Command command = getCommandFromInput(input);
                if (input.equals("execute_script "+file.getPath())){
                    return true;
                }
                if (command.getParametersCount()+1 != input.split(" ").length) {
                    throw new WrongNumberOfParametersException();
                }
            }
        }catch (Exception e){

        }
        return false;
    }

    /**
     * Метод, возвращающий объект класса StudyGroup
     * @param scanner обертка для потока ввода
     * @return studyGroup
     */
    public static StudyGroup getElementFromUser(Scanner scanner){
            String nameS; String name = null;
            do {
                try {
                    System.out.println("Введите название учебной группы");
                    nameS = scanner.nextLine();
                    if(checkIfExit(nameS)){return null;}
                    name = FieldSetterClass.getAdminName(nameS);
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }

            } while (name == null);

            String coordinatesS; Coordinates coordinates  = null;
            do {
                try {
                    System.out.println("Введите координаты учебной группы");
                    coordinatesS = scanner.nextLine();
                    if(checkIfExit(coordinatesS)){return null;}
                    coordinates = FieldSetterClass.getGroupCoordinates(coordinatesS);
                } catch (NumberOutOfBoundsException e){
                    System.out.println("Значение не входит в установленные рамки");
                }
                catch (WrongAmountOfCoordinatesException e){
                    System.out.println("Неверное количество координат");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            } while(coordinates == null);

            String studentsCountS; Long studentsCount = null;
            do {
                try {
                    System.out.println("Введите количество студентов в группе");
                    studentsCountS = scanner.nextLine();
                    if(checkIfExit(studentsCountS)){return null;}
                    studentsCount = FieldSetterClass.getGroupStudentsCount(studentsCountS);
                }
                catch (NumberOutOfBoundsException e){
                    System.out.println("Значение не входит в установленные рамки");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (studentsCount == null);

            String expelledStudentsS; Integer expelledStudents = null;
            do {
                try {
                    System.out.println("Введите количество отчисленных студентов");
                    expelledStudentsS = scanner.nextLine();
                    if(checkIfExit(expelledStudentsS)){return null;}
                    expelledStudents = FieldSetterClass.getGroupExpelledStudents(expelledStudentsS);
                }
                catch (NumberOutOfBoundsException e){
                    System.out.println("Значение не входит в установленные рамки");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (expelledStudents == null);



            String shouldBeExpelledS; int shouldBeExpelled = 0;
            do {
                try {
                    System.out.println("Введите количество студентов, представленных к отчислению");
                    shouldBeExpelledS = scanner.nextLine();
                    if(checkIfExit(shouldBeExpelledS)){return null;}
                    shouldBeExpelled = FieldSetterClass.getGroupShouldBeExpelled(shouldBeExpelledS);
                }catch (NumberOutOfBoundsException e){
                    System.out.println("Значение не входит в установленные рамки");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (shouldBeExpelled == 0);

            String semesterS; Semester semesterEnum = null;
            do {
                try {
                    System.out.println("Введите семестр");
                    System.out.println("Доступные варианты: " + Arrays.toString(Semester.values()));
                    semesterS = scanner.nextLine();
                    if(checkIfExit(semesterS)){return null;}
                    semesterEnum = FieldSetterClass.getGroupSemesterEnum(semesterS);
                }catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (semesterEnum == null);

            String adminNameS; String adminName = null;
            do {
                try{
                System.out.println("Введите имя админа группы");
                adminNameS = scanner.nextLine();
                if(checkIfExit(adminNameS)){return null;}
                adminName = FieldSetterClass.getAdminName(adminNameS);
                }
                catch (NullPointerException e){
                    System.out.println("Значение не может быть null");
                }
            }while (adminName == null);

            String adminHeightS; Long adminHeight = null;
            do {
                try {
                    System.out.println("Введите рост админа группы");
                    adminHeightS = scanner.nextLine();
                    if(checkIfExit(adminHeightS)){return null;}
                    adminHeight = FieldSetterClass.getAdminHeight(adminHeightS);
                }catch (NumberOutOfBoundsException e){
                    System.out.println("Значение не входит в установленные рамки");
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (adminHeight == null);

            String hairColorS; Color hairColor = null;
            do {
                try {
                    System.out.println("Введите цвет волос админа группы");
                    System.out.println("Доступные варианты - " + Arrays.toString(Color.values()));
                    hairColorS = scanner.nextLine();
                    if(checkIfExit(hairColorS)){return null;}
                    hairColor = FieldSetterClass.getAdminHairColor(hairColorS);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while(hairColor == null);

            String nationalityS; Country nationality = null;
            do {
                try {
                    System.out.println("Введите национальность админа группы");
                    System.out.println("Доступные варианты - " + Arrays.toString(Country.values()));
                    nationalityS = scanner.nextLine();
                    if(checkIfExit(nationalityS)){return null;}
                    nationality = FieldSetterClass.getAdminNationality(nationalityS);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
            }while (nationality == null);

            String locationS; Location location = null;
            do {
                try {
                    System.out.println("Введите местоположение админа группы");
                    locationS = scanner.nextLine();
                    if(checkIfExit(locationS)){return null;}
                    location = FieldSetterClass.getAdminLocation(locationS);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Значение неверного формата");
                }
                catch (NullPointerException e){
                    System.out.println("Это поле не может быть null");
                }
                catch (WrongAmountOfCoordinatesException e){
                    System.out.println("Неверное количество координат");
                }
            }while (location == null);
            Person groupAdmin = new Person(adminName, adminHeight, hairColor, nationality, location);
            StudyGroup studyGroup = new StudyGroup(name, coordinates, studentsCount, expelledStudents, shouldBeExpelled,
                    semesterEnum, groupAdmin);

            return studyGroup;
        }

        private static boolean checkIfExit(String s){
            if (s.equals("exit")){
                System.out.println("Вы хотите прекратить ввод? (y/n)");
                String answer = new Scanner(System.in).nextLine();
                if (answer.equals("y")){
                    return true;
                }
            }
            return false;
        }


    /**
     * Метод, возвращающий объект класса StudyGroup из скрипта
     * @param scanner обертка для потока ввода
     * @return studyGroup
     */
        public static StudyGroup getElementFromScript(Scanner scanner) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        String nameS = scanner.nextLine();
        String name = FieldSetterClass.getAdminName(nameS);

        String coordinatesS = scanner.nextLine();
        Coordinates coordinates = FieldSetterClass.getGroupCoordinates(coordinatesS);

        String studentsCountS = scanner.nextLine();
        Long studentsCount = FieldSetterClass.getGroupStudentsCount(studentsCountS);

        String expelledStudentsS = scanner.nextLine();
        Integer expelledStudents = FieldSetterClass.getGroupExpelledStudents(expelledStudentsS);

        String shouldBeExpelledS = scanner.nextLine();
        int shouldBeExpelled = FieldSetterClass.getGroupShouldBeExpelled(shouldBeExpelledS);

        String semesterS = scanner.nextLine();
        Semester semesterEnum = FieldSetterClass.getGroupSemesterEnum(semesterS);

        String adminNameS = scanner.nextLine();
        String adminName = FieldSetterClass.getAdminName(adminNameS);

        String adminHeightS = scanner.nextLine();
        Long adminHeight = FieldSetterClass.getAdminHeight(adminHeightS);

        String hairColorS = scanner.nextLine();
        Color hairColor = FieldSetterClass.getAdminHairColor(hairColorS);

        String nationalityS = scanner.nextLine();
        Country nationality = FieldSetterClass.getAdminNationality(nationalityS);

        String locationS = scanner.nextLine();
        Location location = FieldSetterClass.getAdminLocation(locationS);


        Person groupAdmin = new Person(adminName, adminHeight, hairColor, nationality, location);
        StudyGroup studyGroup = new StudyGroup(name, coordinates, studentsCount, expelledStudents, shouldBeExpelled,
                semesterEnum, groupAdmin);

        return studyGroup;
    }

    /**
     * Метод, проверяющий, включен ли скриптовый режим
     * @return isScriptMode
     */
    public static boolean isScriptMode(){
        return isScriptMode;
    }

}
