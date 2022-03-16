package commands;

import exceptions.NumberOutOfBoundsException;
import utils.Collection;
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
    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner) throws NumberOutOfBoundsException {
        s = s.split(" ")[1];
        ValidationClass.checkLong(s, false, true, Long.MIN_VALUE);
        Long longKey = Long.valueOf(s);
        Collection.remove(longKey);
        System.out.println(separatorString);
    }
}
