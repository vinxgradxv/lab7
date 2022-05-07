package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;
import utils.ValidationClass;

import java.util.Scanner;
/**
 * Класс, описывающий команду remove_key
 */
public class RemoveKeyCommand extends Command{
    /**
     * Конструктор
     */
    public RemoveKeyCommand(){
        parametersCount = 1;
        setParameters("key");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "remove_key";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Удаляет элемент из коллекции по ключу";
    }


    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection) throws NumberOutOfBoundsException {
        Long longParam = (Long) param;
        if (!studyGroupCollection.remove(longParam)){
            return new Response(ResponseType.ERROR, "В коллекции нет элемента с таким ключом");
        }
        return new Response(ResponseType.RESULT, "Элемент был удален из коллекции");
    }
}