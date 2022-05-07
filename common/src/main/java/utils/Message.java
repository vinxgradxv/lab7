package utils;

import commands.Command;
import data.StudyGroup;

import java.io.Serializable;

public class Message implements Serializable {
    private final Command command;
    private final Object param;
    private final StudyGroup studyGroup;

    public Message(Command command, Object param, StudyGroup studyGroup){
        this.command = command;
        this.param = param;
        this.studyGroup = studyGroup;
    }

    public Command getCommand(){return command;}
    public Object getParam(){return param;}
    public StudyGroup getStudyGroup(){return studyGroup;}
}
