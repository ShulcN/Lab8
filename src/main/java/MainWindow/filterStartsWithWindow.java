package MainWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class filterStartsWithWindow {

    private static String name;


    public static void startWindow(ResourceBundle resourceBundle){
        TextField textField = new TextField();
        Button button = new Button();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Pane pane =new Pane();
        textField.setPromptText(resourceBundle.getString("nameStarts"));
        button.setText(resourceBundle.getString("cancel"));
        button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name=null;
                stage.close();
            }
        });
        textField.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name=textField.getText();
            }
        });
        textField.setPrefHeight(40);
        textField.setPrefWidth(360);
        button.setPrefHeight(20);
        button.setPrefWidth(100);
        button.setLayoutY(50);
        button.setLayoutX(160);
        textField.setLayoutX(20);
        textField.setLayoutX(20);
        stage.setResizable(false);
        Scene scene = new Scene(pane, 400, 100);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (textField.getText() != null && !textField.getText().equals("")) {
                    name = textField.getText();
                    stage.close();
                }
            }
        });
        pane.getChildren().addAll(textField, button);
        stage.setScene(scene);
        stage.setTitle("Filter starts with name");
        stage.showAndWait();
    }

    public static String getName() {
        return name;
    }
}
