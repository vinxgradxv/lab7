package commands;

import data.StudyGroup;
import utils.Collection;

import java.util.Scanner;
/**
 * Класс, описывающий команду show
 */
public class ShowCommand extends Command{
    /**
     * Конструктор
     */
    public ShowCommand(){
        parametersCount = 0;
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "show";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Выводит все элементы коллекции в строковом представлении";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner){
        if(Collection.getStudyGroupHashtable().size() == 0){
            System.out.println("На данный момент в коллекции нет элементов");
        }
        for(StudyGroup studyGroup:Collection.getStudyGroupHashtable().values()){
            System.out.println(studyGroup.toString());
            System.out.println();
        }
        System.out.println(separatorString);
    }
}
