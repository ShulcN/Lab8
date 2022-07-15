package Visualisation;

import Given.Product;
import MainWindow.graphic;
import MainWindow.mainWindowController;
import extractedButtons.StartAddWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**Класс создания отдельной визуализации объекта*/
public class NewCanvas {
    public static Canvas newCanvas(Product product, Label label, AnchorPane productArea){
        Canvas pCan = new Canvas();

        pCan.setWidth(graphic.SizeOfFigure(product));
        pCan.setHeight(graphic.SizeOfFigure(product));
        double layX=product.getCoordinates().getX();
        double layY=product.getCoordinates().getY();

        pCan.setLayoutX(layX*productArea.getWidth()/865);
        pCan.setLayoutY(layY*productArea.getHeight()/342);

        GraphicsContext graphicsContext=pCan.getGraphicsContext2D();
        graphicsContext.setFill(graphic.getColor(product));

        pCan.setOpacity(0);
        pCan.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (mouseEvent.getClickCount()==2){
                StartAddWindow.startUpdateWindow(mainWindowController.getResources(), product.getId());
            }else {
                label.setText(product.toString());
            }
        });
        Image image = new Image(product.getType().getImgPath());
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage(pixelReader, (int)image.getWidth(), (int)image.getHeight());
        PixelWriter pixelWriter =writableImage.getPixelWriter();
        for (int y=0;y<image.getHeight();y++){
            for (int x=0; x<image.getWidth();x++){
                if (pixelReader.getColor(x, y).equals(Color.BLACK)){
                    pixelWriter.setColor(x, y, graphic.getColor(product));
                }
            }
        }
        graphicsContext.drawImage(writableImage, 0, 0, pCan.getHeight(), pCan.getWidth());
        return pCan;
    }
}
