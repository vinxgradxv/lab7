package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Response;
import utils.ResponseType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class LoginController implements Initializable {
    Stage stage;
    @FXML
    public TextField loginField;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected ChoiceBox<String> languageChoice;
    @FXML
    protected Label errorLabel;

    private ResourceBundle rb = ResourceBundle.getBundle("localization.Resource", Locale.US);

    private Scene scene;
    private Parent root;
    
    private Client client = new Client();

    private final String[] languages = {"English", "Russian", "Serbian", "Italian", "Spanish (Nicaragua)"};
    private final String[] langCodes = {"_en_US", "_ru_RU", "_sr_SR", "_it_IT", "_es_NI"};
    private final Locale[] locales = {Locale.US, new Locale("ru", "RU"), new Locale("sr", "SR"),Locale.ITALY, new Locale("es", "NI")};

    private String curLogin = "";
    private String curPassword = "";
    private String curLang = languages[0];



    @FXML
    protected void loginButtonClick(ActionEvent e){
        errorLabel.setVisible(false);
        if (!isEmpty()){
            Response response = client.logInUser(loginField.getText(), passwordField.getText());
            if (response == null){
                errorLabel.setText(rb.getString("Server error, try again!"));
                errorLabel.setVisible(true);
                errorLabel.setStyle("-fx-text-fill: red;");
            }
            else if (response.getType() == ResponseType.USER){
                switchToTable(e);
            }
            else {
                errorLabel.setVisible(true);
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    protected void signUpButtonClick(ActionEvent e){
        errorLabel.setVisible(false);
        if (!isEmpty()){
            Response response = client.signUpUser(loginField.getText(), passwordField.getText());
            if (response == null){
                errorLabel.setText(rb.getString("Server error, try again!"));
                errorLabel.setVisible(true);
                errorLabel.setStyle("-fx-text-fill: red;");
            }
            else if (response.getMessage().contains(rb.getString("User with this login already exists"))){
                errorLabel.setText(response.getMessage());
                errorLabel.setVisible(true);
                errorLabel.setStyle("-fx-text-fill: red;");
            }
            else {
                switchToTable(e);
            }
        }
    }

    @FXML
    protected void languageDrag(ActionEvent event){
        System.out.println("asfasdf");
        for (int i = 0; i < languages.length; i++){
            if (languageChoice.getValue().equals(languages[i])){
                try {
                    rb = ResourceBundle.getBundle("localization.Resource", locales[i]);
                    curLogin = loginField.getText();
                    curPassword = passwordField.getText();
                    curLang = languages[i];
                    root = FXMLLoader.load(getClass().getResource("login1.fxml"), rb);
                    stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    LoginApp.locale = locales[i];
                    new LoginApp().start(stage);
                    System.out.println(i);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isEmpty(){
        boolean res = false;
        if (loginField.getText().equals("")){
            loginField.setPromptText(rb.getString("this field is required"));
            loginField.setStyle("-fx-prompt-text-fill: red;");
            res = true;
        }
        if (passwordField.getText().equals("")){
            passwordField.setPromptText(rb.getString("this field is required"));
            passwordField.setStyle("-fx-prompt-text-fill: red;");
            res = true;
        }
        return res;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languageChoice.getItems().addAll(languages);
        loginField.setText(curLogin);
        passwordField.setText(curPassword);
        languageChoice.setValue(curLang);
        languageChoice.setValue(languages[0]);
        languageChoice.setOnAction(this::languageDrag);
    }

    private void switchToTable(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("table1.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
