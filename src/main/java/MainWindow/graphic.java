package MainWindow;

import Given.Product;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class graphic {
    private static HashMap<String, String> colors = new HashMap<>();
    public  static double SizeOfFigure(Product product){
        double price = product.getPrice();
        if (price>10000) return 70;
        if (price>1000) return 50;
        if (price>500) return 35;
        if (price>100) return 25;
        return 15;
    }
    public static Color getColor(Product product){
        return Color.valueOf(colors.get(product.getLogin()));
    }
    public static void setColor(String l, String s){
        colors.put(l, s);
    }
    public static void setColors(HashMap<String, String> colors) {
        graphic.colors = colors;
    }
}
