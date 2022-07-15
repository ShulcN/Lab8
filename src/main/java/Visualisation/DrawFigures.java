package Visualisation;

import Given.Product;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.LinkedHashSet;
/**Класс отрисовки всех объектов*/
public class DrawFigures {

    public static void drawFigures(LinkedHashSet<Product> products, String mes, HashMap<Product, Canvas> canvasHashMap, AnchorPane productArea, Label label) {
        canvasHashMap.clear();
        for (Product p : products) {
            canvasHashMap.put(p, NewCanvas.newCanvas(p, label, productArea));
            productArea.getChildren().add(canvasHashMap.get(p));
            OpacityChange.opacityChange(canvasHashMap.get(p));
        }
    }
}
