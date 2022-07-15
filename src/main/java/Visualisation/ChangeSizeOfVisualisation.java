package Visualisation;

import Given.Product;
import MainWindow.mainWindowController;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
/**Класс для пропорционального изменения положения объектов визуализации при изменении размера окна*/
public class ChangeSizeOfVisualisation {
    public synchronized static void changeHeightOfVisualisation(double oldVal, double newVal, HashMap<Product, Canvas> canvasHashMap, AnchorPane productArea){
        for (Product p : mainWindowController.products) {
            try {
                canvasHashMap.get(p).setLayoutY(newVal / oldVal * canvasHashMap.get(p).getLayoutY());
                productArea.getChildren().add(canvasHashMap.get(p));
            } catch (NullPointerException ignored){

            }
        }
    }
    public synchronized static void changeWidthOfVisualisation(double oldVal, double newVal, HashMap<Product, Canvas> canvasHashMap, AnchorPane productArea){
        for (Product p : mainWindowController.products) {
            try {
                canvasHashMap.get(p).setLayoutX(newVal / oldVal * canvasHashMap.get(p).getLayoutX());
                productArea.getChildren().add(canvasHashMap.get(p));
            } catch (NullPointerException ignored){

            }
        }
    }
}
