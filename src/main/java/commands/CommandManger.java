package commands;

import java.util.*;

/**
 * Класс для взаимодействия с командами
 */

public class CommandManger {
    /**
     * Список всех команд
     */
    private static List<Command> commands = new ArrayList<>();

    /**
     * Метод, добавляющий команды в список
     */
    public static void setCommands(){
        commands.add(new HelpCommand());
        commands.add(new InfoCommand());
        commands.add(new ShowCommand());
        commands.add(new InsertCommand());
        commands.add(new UpdateCommand());
        commands.add(new RemoveKeyCommand());
        commands.add(new ClearCommand());
        commands.add(new SaveCommand());
        commands.add(new ExecuteScriptCommand());
        commands.add(new ExitCommand());
        commands.add(new RemoveLowerCommand());
        commands.add(new ReplaceIfGraterCommand());
        commands.add(new RemoveGreaterKeyCommand());
        commands.add(new SumCommand());
        commands.add(new CountGreaterSemesterCommand());
        commands.add(new FilterLessStCountCommand());
    }

    /**
     *Метод, возвращающий список всех команд
     * @return list of all commands
     */
    public static List<Command> getCommands(){
        return commands;
    }

}
