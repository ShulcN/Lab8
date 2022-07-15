package extractedButtons;

import MainWindow.typeOfWindowAdd;
import MainWindow.addWindow;
import MainWindow.updateWindowController;
import com.example.lab8.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ResourceBundle;

public class StartAddWindow {
    public static void startAddWindow(ResourceBundle resources, typeOfWindowAdd addType) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        addWindow.setAddType(addType.getType());
        loader.setLocation(HelloApplication.class.getResource("WindowAdd.fxml"));
        loader.setResources(resources);
        Scene scene=null;
        try {
            scene = new Scene(loader.load(), 458, 517);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Add");
        stage.show();
    }
    public static void startUpdateWindow(ResourceBundle resources, Long id) {
        Stage stage = new Stage();
        if (id!=null)
        updateWindowController.setPreId(id);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource("UpdateWindow.fxml"));
        loader.setResources(resources);
        Scene scene=null;
        try {
            scene = new Scene(loader.load(), 458, 517);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Update");
        stage.show();
    }
}
