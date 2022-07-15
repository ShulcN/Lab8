package Visualisation;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;

/**Анимация при отрисовке объектов*/
public class OpacityChange {
    public static void opacityChange(Canvas canvas){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(canvas);
        fadeTransition.setDuration(Duration.millis(600));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
}
