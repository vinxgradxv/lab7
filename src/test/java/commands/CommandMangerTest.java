package commands;

import exceptions.CommandNotFoundException;
import org.junit.jupiter.api.Test;
import utils.UserInterface;

import static org.junit.jupiter.api.Assertions.*;

class CommandMangerTest {

    @Test
    void setCommands() throws CommandNotFoundException {
        CommandManger.setCommands();
        assertTrue(UserInterface.getCommandFromInput("info").getClass() == InfoCommand.class);
    }

    @Test
    void getCommandsSize() {
        CommandManger.setCommands();
        assertEquals(CommandManger.getCommands().size(),16);
    }
}