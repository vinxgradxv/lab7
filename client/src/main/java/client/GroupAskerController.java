package client;

import data.Color;
import data.Semester;
import data.StudyGroup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import sun.rmi.runtime.Log;
import utils.Asker;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupAskerController implements Initializable {
    @FXML
    public TextField keyField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField coordinatesXField;
    @FXML
    public TextField coordinatesYField;
    @FXML
    public TextField studentsCountField;
    @FXML
    public TextField expelledStudentsField;
    @FXML
    public TextField shouldBeExpelledField;
    @FXML
    public TextField semesterField;
    @FXML
    public TextField adminNameField;
    @FXML
    public TextField heightField;
    @FXML
    public TextField hairColorField;
    @FXML
    public TextField nationalityField;
    @FXML
    public TextField locationXField;
    @FXML
    public TextField locationYField;
    @FXML
    public TextField locationZField;

    public boolean updateMode = false;

    public static ResourceBundle rb;

    public static StudyGroup studyGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onSendButtonAction(){
        Asker asker = new Asker();
        String name = asker.ask(arg -> arg, arg -> arg.length() > 0, nameField.getText(), false);
        Long coordinatesX = asker.ask(Long::valueOf, arg -> arg <= 722, coordinatesXField.getText(), false);
        Long coordinatesY = asker.ask(Long::valueOf, arg -> arg <= 102, coordinatesYField.getText(), false);
        Long studentsCount = asker.ask(Long::valueOf, arg -> arg > 0, studentsCountField.getText(), false);
        Integer expelledStudents = asker.ask(Integer::valueOf, arg -> arg > 0, expelledStudentsField.getText(), true);
        int shouldBeExpelled = asker.ask(Integer::valueOf, arg -> arg > 0, shouldBeExpelledField.getText(), false);
        Semester semester = asker.ask(Semester::valueOf, arg -> true, semesterField.getText(), true);
        String adminName = asker.ask(arg -> arg, arg -> arg.length() > 0, adminNameField.getText(), false);
        Long height = asker.ask(Long::valueOf, arg -> arg > 0, heightField.getText(), true);
        Color hairColor = asker.ask(Color::valueOf, arg -> true, hairColorField.getText(), )
        LoginController.client.insert()
    }
}
