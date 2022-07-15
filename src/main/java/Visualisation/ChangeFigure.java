package Visualisation;

import Given.Product;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

import java.util.HashMap;

public class ChangeFigure {
    public static void changeFigureAnim(Product product, HashMap<Product, Canvas> canvasHashMap){
        double oldX=product.getCoordinates().getX()*4;
        double oldY=product.getCoordinates().getY();
        try {
            oldX=canvasHashMap.get(product).getLayoutX();
            oldY=canvasHashMap.get(product).getLayoutY();
        } catch (NullPointerException e){

        }
        TranslateTransition tt = new TranslateTransition(Duration.millis(600), canvasHashMap.get(product));
        tt.setFromX(oldX);
        tt.setFromY(oldY);
        double layX=product.getCoordinates().getX()*4;
        double layY=product.getCoordinates().getY();
        tt.setCycleCount(1);
        if (!(oldY==layY)){
            tt.setToY(layY);
        }
        if (!(oldX==layX)) {
            tt.setToX(layX);
        } else {
            tt.setByX(20f);
            tt.setCycleCount(3);
        }
        //tt.setByX(10f);

        tt.setAutoReverse(true);
        tt.playFromStart();
    }
}
