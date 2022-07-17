package SignInWindow;

import com.example.lab8.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
/**класс инициализации рабочего окна программы*/
public class SignInWindow extends Application {

    public SignInWindow(){
    }
    public void StartWindow(){
        launch();
    }
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //fxmlLoader.setResources(ResourceBundle.getBundle("Locale", new Locale("es")));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/lab8/hello-view.fxml"));
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\Locale_rus.properties", new Locale("Rus"));
        //ResourceBundle resourceBundle = ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("Rus"));
        Locale.setDefault(new Locale("rus"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("rus"));
        System.out.println(resourceBundle.getString("Add"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), 560, 400);
        HelloApplication.stage.setTitle("Registration");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.setResizable(false);
        //HelloApplication.stage.setResizable(false);
        HelloApplication.stage.show();

    }

}
