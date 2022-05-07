package commands;

import data.StudyGroup;
import utils.CollectionManager;
import utils.Response;
import utils.ResponseType;

import java.util.Scanner;
/**
 * Класс, описывающий команду help
 */
public class HelpCommand extends Command{
    /**
     * Конструктор
     */
    public HelpCommand(){
        parametersCount = 0;
    }

    /**
     * Метод, возвращающий имя команды
     * @return name of the command
     */
    @Override
    public String getName() {
        return "help";
    }
    /**
     * Метод, возвращающий информацию о команде
     * @return information about the command
     */
    @Override
    public String getInfo() {
        return "Выводит справку по доступным командам";
    }


    public Response execute(Object param, StudyGroup studyGroup, CollectionManager studyGroupCollection){
        String result = "";
        for(Command command:CommandManger.getCommands()){
            result += command.getName() + " - " + command.getInfo()+"\n";
        }
        return new Response(ResponseType.RESULT, result);
    }
}
