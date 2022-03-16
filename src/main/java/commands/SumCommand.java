package commands;

import data.StudyGroup;
import utils.Collection;

import java.util.Scanner;
/**
 * Класс, описывающий команду sum_of_students_count
 */
public class SumCommand extends Command{
    /**
     * Конструктор
     */
    public SumCommand(){
        parametersCount = 0;
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "sum_of_students_count";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Выводит сумму значений поля studentsCount всех элементов коллекции";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner){
        Long sum = 0L;
        for(StudyGroup studyGroup: Collection.getStudyGroupHashtable().values()){
            sum += studyGroup.getStudentsCount();
        }
        System.out.println("Сумма полей studentsCount для всех элементов = " + sum);
        System.out.println(separatorString);
    }
}
