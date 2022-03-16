package commands;

import utils.Collection;
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

    /**
     * Метод, исполняющий команду
     * @param s string for extract parameters from
     * @param scanner object that we use to read information from user input
     *                or script
     */
    public void execute(String s, Scanner scanner){
        Collection.clear();
        System.out.println("Коллекция очищена");
        System.out.println(separatorString);
    }
}
