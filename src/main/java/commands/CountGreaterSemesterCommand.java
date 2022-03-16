package commands;

import data.Semester;
import data.StudyGroup;
import utils.Collection;
import utils.ValidationClass;

import java.util.Scanner;

/**
 * Класс, описывающий команду count_greater_than_semester_enum
 */
public class CountGreaterSemesterCommand extends Command{
    /**
     * Конструктор
     */
    public CountGreaterSemesterCommand(){
        parametersCount = 1;
        setParameters("semesterEnum");
    }

    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "count_greater_than_semester_enum";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Выводит количество элементов, у которых semesterEnum больше заданного";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner){
        s = s.split(" ")[1];
        ValidationClass.checkSemester(s, false);
        Semester semester = Semester.valueOf(s);
        Long count = 0L;
        for(StudyGroup studyGroup: Collection.getStudyGroupHashtable().values()){
            if(studyGroup.getSemesterEnum().compareTo(semester)>0){
                count += 1;
            }
        }
        System.out.println("Количество элементов коллекции с semesterEnum больше заданного = " + count);
        System.out.println(separatorString);
    }
}
