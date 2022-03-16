package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import utils.Collection;
import utils.ValidationClass;

import java.util.Scanner;
/**
 * Класс, описывающий команду filter_less_than_students_count
 */
public class FilterLessStCountCommand extends Command{
    /**
     * Конструктор
     */
    public FilterLessStCountCommand(){
        parametersCount = 1;
        setParameters("studentsCount");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "filter_less_than_students_count";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Выводит все элементы коллекции, значение поле studentsCount меньше, чем заданное";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException {
        s = s.split(" ")[1];
        int counter = 0;
        ValidationClass.checkLong(s,false, true, 0L);
        Long studentsCount = Long.valueOf(s);
        for (StudyGroup studyGroup: Collection.getStudyGroupHashtable().values()){
            if (studyGroup.getStudentsCount() < studentsCount){
                counter += 1;
                System.out.println(studyGroup.toString());
            }
        }
        if (counter == 0){
            System.out.println("Подходящих элементов нет");
        }
        System.out.println(separatorString);

    }
}
