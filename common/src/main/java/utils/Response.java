package utils;

import data.User;

import java.io.Serializable;

public class Response implements Serializable {
    private final ResponseType type;
    private final String message;
    private final User user;

    public Response(ResponseType type, String message, User user){
        this.type = type;
        this.message = message;
        this.user = user;
    }

    public ResponseType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public User getUser(){return user;}
}
