package MainWindow;

import Command.PersonalInfoUpdate;
import Connection.SendCommand;
import Given.Country;
import Given.Person;
import com.example.lab8.HelloApplication;
import com.example.lab8.PersonalInfo;
import extractedButtons.BackButton;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;

public class userDataController {
    @FXML
    private Button SignInButton;

    @FXML
    private Label UserLabel;

    @FXML
    private ChoiceBox<Country> nationality;

    @FXML
    private TextField ownerName;

    @FXML
    private Spinner<Double> weightField;
    ObservableList<Country> countries = FXCollections.observableArrayList(Country.CHINA, Country.INDIA, Country.GERMANY, Country.NORTH_KOREA);
    @FXML
    void initialize() {
        UserLabel.setStyle("-fx-background-radius: 40; -fx-background-color: #cfcfcf; -fx-text-fill: "+PersonalInfo.getHexColor());
        UserLabel.setText(mainWindowController.getStringBinding("User")+": "+PersonalInfo.getLogin());
        nationality.setItems(countries);
        nationality.setValue(PersonalInfo.getNationality());
        if (PersonalInfo.getWeight()!=null){
            weightField.getValueFactory().setValue((double) PersonalInfo.getWeight());
        }
        ownerName.setText(PersonalInfo.getName());
        SignInButton.setOnAction(e -> {
            SignInButton.setOpacity(0.8);
            PauseTransition pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(ev -> SignInButton.setOpacity(1.0));
            pause.play();
            enter();
        });
    }
    public void changePassword(){
        changePasswordWindow.startWindow(mainWindowController.getResources());
    }
    private void enter(){
        String ownerNameText=ownerName.getText();
        if(ownerNameText==null || ownerNameText.isEmpty()){
            Shake(ownerName);
            return;
        }
        Country nationalityValue=nationality.getValue();
        String weightStr = weightField.getValue().toString();
        Float weight = Float.parseFloat(weightStr);
        if (weight==0f)weight=null;
        Person owner = SendCommand.LogIn(new PersonalInfoUpdate(PersonalInfo.getLogin(), ownerNameText, nationalityValue, weight));
        if (owner != null) {
            if (owner.getWeight()!=null) {
                PersonalInfo.setWeight(owner.getWeight());
            }
            PersonalInfo.setNationality(owner.getNationality());
            PersonalInfo.setName(owner.getName());
        } else {
            System.out.println("wtf");
        }
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
        ((Stage)nationality.getScene().getWindow()).close();
    }

}
