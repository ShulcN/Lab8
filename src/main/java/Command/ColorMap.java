package Command;

import javafx.scene.paint.Color;

import java.util.HashMap;

public class ColorMap {
    private static HashMap<Integer, Color> ColorMap = new HashMap<Integer, Color>();
    {
        ColorMap.put(1, Color.CORAL);
        ColorMap.put(2, Color.CORAL);
        ColorMap.put(3, Color.AQUA);
        ColorMap.put(4, Color.RED);
        ColorMap.put(5, Color.BLUE);
        ColorMap.put(6, Color.GOLD);
        ColorMap.put(7, Color.GREEN);
        ColorMap.put(8, Color.YELLOW);
        ColorMap.put(9, Color.WHITE);
        ColorMap.put(10, Color.PURPLE);
        ColorMap.put(11, Color.GAINSBORO);
        ColorMap.put(12, Color.SILVER);
        ColorMap.put(13, Color.CHOCOLATE);
        ColorMap.put(14, Color.BLACK);
        ColorMap.put(15, Color.BROWN);
        ColorMap.put(16, Color.ORANGE);
        ColorMap.put(17, Color.TEAL);
        ColorMap.put(18, Color.VIOLET);
    }

    public static Color getColorMap(int i) {
        return ColorMap.get(i);
    }
}
