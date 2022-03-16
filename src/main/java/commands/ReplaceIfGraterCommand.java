package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.Collection;
import utils.UserInterface;
import utils.ValidationClass;

import java.util.Scanner;
/**
 * Класс, описывающий команду replace_if_greater
 */
public class ReplaceIfGraterCommand extends Command{
    /**
     * Конструктор
     */
    public ReplaceIfGraterCommand(){
        parametersCount = 1;
        setParameters("key");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "replace_if_greater";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Заменяет значение по ключу, если новое значение больше старого";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        ValidationClass.checkLong(s, false, true, Long.MIN_VALUE);
        Long key = Long.valueOf(s);
        StudyGroup studyGroup;
        if (UserInterface.isScriptMode()) {
            studyGroup = UserInterface.getElementFromScript(scanner);
        } else {
            studyGroup = UserInterface.getElementFromUser(scanner);
        }


        if (studyGroup != null) {
            if (Collection.getStudyGroupHashtable().get(key).compareTo(studyGroup) < 0) {
                Collection.add(key, studyGroup);
                System.out.println("Значение было заменено");
            } else {
                System.out.println("Значение не было заменено");
            }
            System.out.println(separatorString);
        }
    }
}
