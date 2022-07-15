package MainWindow;

import Command.PasswordUpdate;
import Connection.SendCommand;
import com.example.lab8.PersonalInfo;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class changePasswordController {
    @FXML
    private Button SignInButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;
    @FXML
    void initialize(){
        SignInButton.setOnAction(e -> {
            SignInButton.setOpacity(0.8);
            PauseTransition pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(ev -> SignInButton.setOpacity(1.0));
            pause.play();
            enter();
        });
    }
    private void enter(){
        String oldPas = passwordField1.getText();
        String newPas = passwordField.getText();
        if (oldPas.isEmpty()){
            Shake(passwordField1);
            return;
        }
        if (newPas.isEmpty()){
            Shake(passwordField);
            return;
        }
        if (!SendCommand.SendCommand(new PasswordUpdate(PersonalInfo.getLogin(), oldPas, newPas)).equals("ChangeSuccess")){
            Shake(passwordField1);
            return;
        }
        mainWindowController.LabelText.setText(mainWindowController.getStringBinding("ChangeSuccess"));
        cancel();
    }
    void Shake(Node node){
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }
    public void cancel(){
        ((Stage)SignInButton.getScene().getWindow()).close();
    }
}
