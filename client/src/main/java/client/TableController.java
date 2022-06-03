package client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javax.swing.table.TableColumn;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {
    @FXML
    protected TableView<TableColumn> table;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
