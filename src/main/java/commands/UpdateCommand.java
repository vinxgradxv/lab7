package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.Collection;
import utils.UserInterface;
import utils.ValidationClass;

import java.util.Hashtable;
import java.util.Scanner;
/**
 * Класс, описывающий команду update
 */
public class UpdateCommand extends Command{
    /**
     * Конструктор
     */
    public UpdateCommand(){
        parametersCount = 1;
        setParameters("id");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "update";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Обновляет значение коллекции с заданным id";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        StudyGroup studyGroup = null;
        boolean isChanged = false;
        s = s.split(" ")[1];
        ValidationClass.checkLong(s, false, true, 0L);
        Long longId = Long.valueOf(s);
        Hashtable<Long, StudyGroup> studyGroupHashtable = Collection.getStudyGroupHashtable();
        for(Long key:studyGroupHashtable.keySet()){
            if(studyGroupHashtable.get(key).getId().equals(longId)){
                isChanged = true;
                break;
            }
        }
        if (!isChanged){
            System.out.println("Не найден элемент с заданным id");
        }
        else
        if(UserInterface.isScriptMode()){
            studyGroup = UserInterface.getElementFromScript(scanner);
        }
        else{
            studyGroup = UserInterface.getElementFromUser(scanner);
        }

        if(studyGroup != null)
        for(Long key:studyGroupHashtable.keySet()){
            if(studyGroupHashtable.get(key).getId().equals(longId)){
                Collection.add(key, studyGroup);
                System.out.println("Элемент с ключом " + key + " был обновлен");
                break;
            }
        }
        System.out.println(separatorString);
    }
}
