package MainWindow;

import Command.RemoveID;
import Connection.SendCommand;
import Given.Product;
import com.example.lab8.PersonalInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class removeWindow {


    @FXML
    private ChoiceBox<Long> Id;

    private static ObservableList<Long> ids=null;
    public static void setIds(LinkedList<Long> ids){
        removeWindow.ids= FXCollections.observableArrayList(ids);
    }

    @FXML
    private Button addButton;

    public static void removeWindow(){

    }

    @FXML
    void initialize(){
        Id.setItems(ids);
    }
    public void cancel(ActionEvent event) {
        ((Stage)addButton.getScene().getWindow()).close();
    }

    public void fillStrings(ActionEvent event) {
        Long id = Id.getValue();
        try {
            if (id==null)throw new Exception();
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        LinkedHashSet<Product> linkedHashSet = SendCommand.SendAndGetCommand(new RemoveID(id.toString(), PersonalInfo.getLogin()));
        mainWindowController.FillTable(linkedHashSet);
    }
}
