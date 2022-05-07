package commands;

import data.StudyGroup;
import exceptions.NullValueException;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;
import utils.ValidationClass;

import java.util.Hashtable;
import java.util.Objects;
import java.util.Scanner;
/**
 * Класс, описывающий команду update
 */
public class UpdateCommand extends Command{
    /**
     * Конструктор
     */
    public UpdateCommand(){
        isElementRequired = true;
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


    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException {
        Long longParam = (Long) param;
        StudyGroup st = null;
        Long kk = 0L;
        for (Long key: studyGroupCollection.getStudyGroupHashTable().keySet()){
            if (studyGroupCollection.getStudyGroupHashTable().get(key).getId() == longParam){
                st = studyGroupCollection.getStudyGroupHashTable().get(key);
                kk = key;
                break;
            }
        }
        if (st == null){
            return new Response(ResponseType.ERROR, "В коллекции нет элемента с таким id");
        }
        try {
            studyGroup.setId(st.getId());
        }
        catch (NullValueException e){
            System.err.println("id не может быть null");
        }
        studyGroupCollection.add(kk, studyGroup);
        return new Response(ResponseType.RESULT, "Элемент обновлен");
    }
}
