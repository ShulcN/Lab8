package extractedButtons;

import com.example.lab8.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ResourceBundle;
/**Кнопка возвращения на страницу входа*/
public class BackButton {
    public static void back(ResourceBundle resources){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("hello-view.fxml"));
        loader.setResources(resources);
        Scene scene;
        try {
            HelloApplication.stage.setResizable(false);
            HelloApplication.stage.setMinHeight(200);
            HelloApplication.stage.setMinWidth(460);
            HelloApplication.stage.setWidth(562);
            HelloApplication.stage.setHeight(420);
            scene = new Scene(loader.load(), 562, 440);
            HelloApplication.stage.setScene(scene);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
