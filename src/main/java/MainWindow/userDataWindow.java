package MainWindow;

import com.example.lab8.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class userDataWindow {
    public static void startWindow(ResourceBundle resources){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("userData.fxml"));
        loader.setResources(resources);
        Scene scene=null;
        try {
            scene = new Scene(loader.load(), 499, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Add");
        stage.show();
    }
}
