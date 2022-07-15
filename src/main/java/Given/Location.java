package Given;


import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = 102L;
    private Integer x; //Поле не может быть null
    private long y;
    private long z;

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public Location(Integer x, long y, long z) throws Exception{
        if (x==null) throw new Exception();
        this.x=x;
        this.y=y;
        this.z=z;
    }
    @Override
    public String toString(){
        return "("+x+","+y+","+z+")";
    }
}
