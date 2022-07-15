package SignInWindow;

import com.example.lab8.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

        ResourceBundle resourceBundle = ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("es"));
        fxmlLoader.setResources(resourceBundle);
        Scene scene = new Scene(fxmlLoader.load(), 560, 400);
        HelloApplication.stage.setTitle("Registration");
        HelloApplication.stage.setScene(scene);
        HelloApplication.stage.setResizable(false);
        //HelloApplication.stage.setResizable(false);
        HelloApplication.stage.show();

    }
}
