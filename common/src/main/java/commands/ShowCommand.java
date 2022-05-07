package commands;

import data.StudyGroup;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;

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


    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection){
        if (studyGroupCollection.getStudyGroupHashTable().isEmpty()){
            return new Response(ResponseType.ERROR, "В коллекции нет элементов");
        }
        String result = "";
        for (StudyGroup st: studyGroupCollection.getStudyGroupHashTable().values()){
            result += st.toString() + "\n";
        }
        return new Response(ResponseType.RESULT, result);
    }
}
