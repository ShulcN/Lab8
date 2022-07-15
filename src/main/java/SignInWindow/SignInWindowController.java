package SignInWindow;

import Connection.ConnectToServ;
import MainWindow.mainWindowController;
import com.example.lab8.HelloApplication;
import extractedButtons.LanguageButton;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**Класс управления окном входа в учетную запись*/
public class SignInWindowController {
    ObservableList<String> languages = FXCollections.observableArrayList("Spanish(Columbian)", "Russian", "Croatian", "Romanian");
    @FXML
    private Label welcomeText;
    @FXML
    private Button SignIn;
    @FXML
    private Button SignUp;
    @FXML
    private TextField LoginText;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button retryButton;
    @FXML
    private ChoiceBox<String> languageBut;


    void Update(){
        passwordField.setPromptText(getStringBinding("password"));
        LoginText.setPromptText(getStringBinding("login"));
        retryButton.setText(getStringBinding("RetryConnect"));
        SignIn.setText(getStringBinding("signIn"));
        SignUp.setText(getStringBinding("Register"));

        if (HelloApplication.getSocket()==null || !HelloApplication.isConnected()){
            welcomeText.setText(getStringBinding("ConnectError"));
            retryButton.setVisible(true);
            retryButton.setOnAction(actionEvent -> {
                retryButton.setOpacity(0.8);
                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(e -> retryButton.setOpacity(1.0));
                pause.play();
                HelloApplication.setSocket(ConnectToServ.Connect());
                Update();
            });
        } else {
            System.out.println(HelloApplication.getSocket().isConnected());
            welcomeText.setText(getStringBinding("Hello"));
            if (retryButton.isVisible())retryButton.setVisible(false);
            SignIn.setOnAction(actionEvent -> {
                SignIn.setOpacity(0.8);
                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(e -> SignIn.setOpacity(1.0));
                pause.play();
                String login = LoginText.getText();
                String password = passwordField.getText();
                try {
                    welcomeText.setText(getStringBinding(SignInBut.ClickOnButton(login, password)));
                } catch (MissingResourceException e){
                    System.out.println("Я работаю над этим");
                    Update();
                }
                if (welcomeText.getText().equals(getStringBinding("AuthorizationSuccess"))){
                    mainWindowController.setResources(resources);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(HelloApplication.class.getResource("MainWindow.fxml"));
                    loader.setResources(resources);
                    try {
                        Scene scene = new Scene(loader.load(), 1044, 570);
                        HelloApplication.stage.setScene(scene);
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
            SignUp.setOnAction(ev-> {
                SignUp.setOpacity(0.8);
                PauseTransition pause = new PauseTransition(Duration.millis(100));
                pause.setOnFinished(e -> SignUp.setOpacity(1.0));
                pause.play();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(HelloApplication.class.getResource("RegistrationWindow.fxml"));
                loader.setResources(resources);
                Scene scene;
                try {
                    scene = new Scene(loader.load(), 600, 400);
                    HelloApplication.stage.setResizable(false);
                    HelloApplication.stage.setMinHeight(460);
                    HelloApplication.stage.setMinWidth(600);
                    HelloApplication.stage.setWidth(600);
                    HelloApplication.stage.setHeight(460);
                    HelloApplication.stage.setScene(scene);
                } catch (IOException e){
                    e.printStackTrace();
                }
            });
        }
        languageBut.setOnAction(e ->{
            setResources(LanguageButton.languageResBun(languageBut.getValue()));
            Update();
        });
    }

    @FXML
    void initialize(){
        languageBut.setItems(languages);
        languageBut.setValue("language");
        Update();
    }

    private ResourceBundle resources=ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("Rus"));

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    private String getStringBinding(String key) {
        return resources.getString(key);
    }


}