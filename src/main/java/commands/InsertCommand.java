package commands;

import data.StudyGroup;
import exceptions.NumberOutOfBoundsException;
import exceptions.WrongAmountOfCoordinatesException;
import utils.Collection;
import utils.UserInterface;
import utils.ValidationClass;

import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import java.util.Scanner;
/**
 * Класс, описывающий команду insert
 */
public class InsertCommand extends Command{
    /**
     * Конструктор
     */
    public InsertCommand(){
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
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException, WrongAmountOfCoordinatesException
    {
        s = s.split(" ")[1];
        StudyGroup value;
        ValidationClass.checkLong(s,false,true,Long.MIN_VALUE);
        if(UserInterface.isScriptMode()){
            value = UserInterface.getElementFromScript(scanner);
        }
        else{
            value = UserInterface.getElementFromUser(scanner);
        }
        if (value != null) {
            Long longKey = Long.valueOf(s);
            Collection.add(longKey, value);
            System.out.println("Элемент был добавлен в коллекцию");
            System.out.println(separatorString);
        }
    }
}
