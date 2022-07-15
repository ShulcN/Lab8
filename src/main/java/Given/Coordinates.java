package Given;


import java.io.Serial;
import java.io.Serializable;

public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 103L;
    private final int x;
    private final Float y;
    public Coordinates(int x, Float y){
        this.x=x;
        this.y=y;
    }
    //public Coordinates()

    public int getX() {
        return x;
    }

    public Float getY() {
        return y;
    }



    @Override
    public String toString(){
        return "("+x+","+y+")";
    }
}
