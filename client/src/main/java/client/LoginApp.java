package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginApp extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = Locale.US;
        System.out.println(locale.getCountry());
        System.out.println(locale.getLanguage());
        ResourceBundle rb = ResourceBundle.getBundle("localization.Resource", locale);
        System.out.println(rb.getBaseBundleName());
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApp.class.getResource("login1.fxml"), rb);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.getIcons().add(new Image("https://i.pinimg.com/564x/3c/4f/72/3c4f72b99983fc5666db256bfdef8774.jpg"));
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}
