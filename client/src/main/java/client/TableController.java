package client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    protected static ResourceBundle rb;

    @FXML
    protected TableView<TableColumn> table;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onHelpButtonClick(){
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("help.fxml"), rb);
            stage.setScene(new Scene(root));
            stage.setTitle(rb.getString("help"));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void onSumButtonAction(){

    }
}
