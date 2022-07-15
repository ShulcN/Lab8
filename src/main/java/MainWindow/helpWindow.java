package MainWindow;

import Command.Help;
import Connection.SendCommand;
import com.example.lab8.PersonalInfo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class helpWindow {

    @FXML
    private Label LabelText;

    @FXML
    private Button cancel;

    @FXML
    void initialize(){
        LabelText.setText(mainWindowController.getStringBinding(SendCommand.SendCommand(new Help(PersonalInfo.getLogin()))));
        cancel.addEventHandler(ActionEvent.ACTION, event -> ((Stage)cancel.getScene().getWindow()).close());
    }
}
