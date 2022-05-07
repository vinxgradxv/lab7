package commands;

import data.StudyGroup;
import exceptions.NullValueException;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;
import utils.ValidationClass;

import java.util.Scanner;
/**
 * Класс, описывающий команду insert
 */
public class InsertCommand extends Command{
    /**
     * Конструктор
     */
    public InsertCommand(){
        isElementRequired = true;
        parametersCount = 1;
        setParameters("key");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "insert";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Добавляет новый элемент с заданным ключом";
    }

    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException
    {
        try {
            studyGroup.setId(studyGroupCollection.getMinFreeId());
            studyGroupCollection.addIdToUsed(studyGroup.getId());
            Long longParam = (Long) param;
            if (studyGroupCollection.getStudyGroupHashTable().containsKey(longParam)) {
                return new Response(ResponseType.ERROR, "Объект с таким ключом уже есть в коллекции");
            }
            studyGroupCollection.add(longParam, studyGroup);
            return new Response(ResponseType.RESULT, "Объект добавлен в коллекцию");
        }catch (NullValueException e){
            System.err.println("id не может быть null");
        }
        return null;
    }

}
