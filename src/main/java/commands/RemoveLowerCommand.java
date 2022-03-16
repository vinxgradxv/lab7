package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.Collection;
import utils.UserInterface;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.Scanner;
/**
 * Класс, описывающий команду remove_lower
 */
public class RemoveLowerCommand extends Command{
    /**
     * Конструктор
     */
    public RemoveLowerCommand(){
        parametersCount = 0;
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "remove_lower";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Удаляет из коллекции элементы, меньшие заданного";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        StudyGroup studyGroup;
        int counter = 0;
        if (UserInterface.isScriptMode()) {
            studyGroup = UserInterface.getElementFromScript(scanner);
        } else {
            studyGroup = UserInterface.getElementFromUser(scanner);
        }

        if (studyGroup != null) {
            for (Long i : Collection.getStudyGroupHashtable().keySet()) {
                if (Collection.getStudyGroupHashtable().get(i).compareTo(studyGroup) < 0) {
                    Collection.remove(i);
                    counter += 1;
                }
            }
            System.out.println("Количество удаленных элементов = " + counter);
            System.out.println(separatorString);
        }
    }
}
