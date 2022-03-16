package commands;

import exceptions.NumberOutOfBoundsException;
import utils.Collection;
import utils.ValidationClass;

import java.util.Scanner;
/**
 * Класс, описывающий команду remove_grater_key
 */
public class RemoveGreaterKeyCommand extends Command{
    /**
     * Конструктор
     */
    public RemoveGreaterKeyCommand(){
        parametersCount = 1;
        setParameters("key");
    }
    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "remove_greater_key";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Удаляет из коллекции все элементы, ключ которых превышает заданный";
    }
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException {
        s = s.split(" ")[1];
        long counter = 0;
        ValidationClass.checkLong(s,false, true, Long.MIN_VALUE);
        Long longKey = Long.valueOf(s);
        for(Long k: Collection.getStudyGroupHashtable().keySet()){
            if (longKey < k){
                Collection.remove(k);
                counter += 1;
            }
        }
        System.out.println("Количество удаленных элементов = " + counter);
        System.out.println(separatorString);
    }
}
