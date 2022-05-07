package utils;

import commands.Command;
import commands.CommandManger;
import data.StudyGroup;
import exceptions.NullValueException;
import exceptions.NumberOutOfBoundsException;

import java.io.IOException;

public class RequestMaker {
    public Message getCommandFromInput(String input, CommandManger commandManger, IOManager ioManager) throws NumberOutOfBoundsException, IOException, NullValueException {
        Command properCommand = null;
        Object parameter = null;
        StudyGroup studyGroup = null;
        try {
            for (Command command : CommandManger.getCommands()) {
                if (input.split(" ")[0].equals(command.getName())) {
                    properCommand = command;
                    break;
                }
            }

            if (ioManager.getFileMode()){
                ioManager.println("Производится выполнение команды " + input + " из скрипта");
            }

            if (properCommand.getParametersCount() == 1) {
                parameter = commandManger.getParameterObject(input);
            }

            if (properCommand.isElementRequired) {
                studyGroup = new AskStudyGroup(ioManager).getStudyGroupFromUser();
            }


            return new Message(properCommand, parameter, studyGroup);
        }catch (NumberFormatException e){
            ioManager.printerr("Параметр не входит в установленные рамки");
        }
        catch (IndexOutOfBoundsException e){
            ioManager.printerr("Для этой команды необходим параметр");
        }catch (IllegalArgumentException e){
            ioManager.printerr("Неверный формат параметра");
        }
        return null;
    }

}
