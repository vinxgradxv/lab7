package utils;

import commands.*;
import data.Coordinates;
import data.Location;
import data.Person;
import data.StudyGroup;
import exceptions.CommandNotFoundException;
import exceptions.WrongAmountOfCoordinatesException;
import exceptions.WrongNumberOfParametersException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    void getCommandFromInputCorrectWithoutParam() throws WrongNumberOfParametersException, CommandNotFoundException {
        CommandManger.setCommands();
        assertTrue(UserInterface.getCommandFromInput("info").getClass() == InfoCommand.class);
    }

    @Test
    void getCommandFromInputCorrectWith1Param() throws WrongNumberOfParametersException, CommandNotFoundException {
        CommandManger.setCommands();
        assertTrue(UserInterface.getCommandFromInput("insert 1").getClass() == InsertCommand.class);
    }


    @Test
    void getCommandFromInputIncorrectCommand(){
        CommandManger.setCommands();
        try {
            UserInterface.getCommandFromInput("xhhmylxrd");
            fail("Expected CommandNotFoundException");
        } catch (CommandNotFoundException e){
            assertTrue(e.getClass() == CommandNotFoundException.class);
        }
    }

    @Test
    void getCommandFromInputParamFirst(){
        CommandManger.setCommands();
        try {
            UserInterface.getCommandFromInput("10 insert");
            fail("Expected CommandNotFoundException");
        } catch (CommandNotFoundException e){
            assertTrue(e.getClass() == CommandNotFoundException.class);
        }
    }


}