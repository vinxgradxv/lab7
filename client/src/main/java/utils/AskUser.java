package utils;

import data.User;

import java.io.IOException;


public class AskUser {

    IOManager ioManager;
    Asker asker;

    public AskUser(IOManager ioManager){
        this.ioManager = ioManager;
        this.asker = new Asker(ioManager);
    }

    public User getUserFromInput() throws IOException {
        String login = asker.ask(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите логин пользователя",
                "логин не может быть пустым",
                false);
        String password = asker.ask(arg -> arg,
                arg -> (arg.length() > 0),
                "Введите пароль пользователя",
                "пароль не может быть пустым",
                false);
        return new User(login, password);
    }
}
