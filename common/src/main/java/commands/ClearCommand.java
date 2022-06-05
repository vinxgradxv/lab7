package commands;

import data.StudyGroup;
import data.User;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;

import java.util.Scanner;

/**
 * Класс, описывающий команду clean
 */
public class ClearCommand extends Command{
    /**
     * Конструктор
     */
    public ClearCommand(){
        parametersCount = 0;
        setParameters();
    }

    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "clear";
    }

    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "очищает коллекцию";
    }


    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection, User user){
        studyGroupCollection.setCurrentUser(user);
        int count = studyGroupCollection.clean();
        if(count > 0){
            return new Response(ResponseType.RESULT, "Коллекция очищена", user, null);
        }
        else if (count == 0) {
            return new Response(ResponseType.ERROR, "В коллекции нет элементов", user, null);
        }
        else return new Response(ResponseType.ERROR, "произошла ошибка во время проведения операции", user, null);
    }
}
