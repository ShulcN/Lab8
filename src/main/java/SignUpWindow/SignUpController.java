package SignUpWindow;

import Command.Registration;
import Connection.SendCommand;
import Given.Country;
import MainWindow.mainWindowController;
import com.example.lab8.HelloApplication;
import com.example.lab8.PersonalInfo;
import extractedButtons.BackButton;
import extractedButtons.LanguageButton;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**Класс управления окном регистрации*/
public class SignUpController {

    ObservableList<Country> countries = FXCollections.observableArrayList(Country.CHINA, Country.INDIA, Country.GERMANY, Country.NORTH_KOREA);

    ObservableList<String> languages = FXCollections.observableArrayList("Spanish(Columbian)", "Russian", "Croatian", "Romanian");



    @FXML
    private Label LabelText;

    @FXML
    private TextField LoginField;
    @FXML
    private ColorPicker ColorBox;


    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private ChoiceBox<Country> nationality;

    @FXML
    private TextField ownerName;
    @FXML
    private Spinner<?> weightField;
    @FXML
    private Button SignInButton;
    @FXML
    private ChoiceBox<String> longuage;
    @FXML
    private Button backBut;

    @FXML
    void initialize() {
        longuage.setItems(languages);
        longuage.setValue("language");
        nationality.setItems(countries);
        SignInButton.setOnAction(e -> {
            SignInButton.setOpacity(0.8);
            PauseTransition pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(ev -> SignInButton.setOpacity(1.0));
            pause.play();
            enter();
        });
        longuage.setOnAction(e ->{
            setResources(LanguageButton.languageResBun(longuage.getValue()));
            Update();
        });
        backBut.setOnAction(event -> {
            BackButton.back(resources);
        });
        Update();
    }
    void Update(){
        SignInButton.setText(getStringBinding("Register"));
        passwordField.setPromptText(getStringBinding("password"));
        passwordField1.setPromptText(getStringBinding("ConfirmPassword"));
        LoginField.setPromptText(getStringBinding("login"));
        LabelText.setText(getStringBinding("FillTheFields"));
    }

    private void enter(){
        String login = LoginField.getText();
        String pas=passwordField.getText();
        if (!pas.isEmpty()){
            if (pas.equals(passwordField1.getText())){
                Color color = ColorBox.getValue();
                String ownerNameText=ownerName.getText();
                if(ownerNameText==null || ownerNameText.isEmpty()){
                    Shake(ownerName);
                    return;
                }
                Country nationalityValue=nationality.getValue();
                if(nationalityValue==null){
                    Shake(nationality);
                    return;
                }
                String weightStr = weightField.getValue().toString();
                Float weight = Float.parseFloat(weightStr);
                if (weight==0f)weight=null;
                try {
                    LabelText.setText(getStringBinding(SendCommand.SendCommand(new Registration(login, pas, color.toString(), ownerNameText, weight, nationalityValue))));
                } catch (MissingResourceException ex){
                    Update();
                }
                if (LabelText.getText().equals(getStringBinding("AuthorizationSuccess"))){
                    PersonalInfo.setLogin(login);
                    PersonalInfo.setName(ownerNameText);
                    PersonalInfo.setNationality(nationalityValue);
                    PersonalInfo.setWeight(weight);
                    PersonalInfo.setHexColor(String.format( "#%02X%02X%02X",
                            (int)( color.getRed() * 255 ),
                            (int)( color.getGreen() * 255 ),
                            (int)( color.getBlue() * 255 ) ));
                    HelloApplication.setColor(color.toString());
                    mainWindowController.setResources(resources);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(HelloApplication.class.getResource("MainWindow.fxml"));
                    loader.setResources(resources);
                    try {
                        Scene scene = new Scene(loader.load(), 1044, 577);
                        HelloApplication.stage.setScene(scene);
                    } catch (IOException ex){
                        ex.printStackTrace();
                    }

                }
            } else {
                LabelText.setText(getStringBinding("SamePassword"));
                Shake(passwordField1);
            }
        } else {
            LabelText.setText(getStringBinding("EmptyPassword"));
            Shake(passwordField);
        }
    }

    private ResourceBundle resources=ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("Rus"));

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    private String getStringBinding(String key) {
        return resources.getString(key);
    }
    void Shake(Node node){
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }
}

