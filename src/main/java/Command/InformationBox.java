package Command;

import Given.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class InformationBox extends Command{
    private static final long serialVersionUID = 45L;
    //  private final LinkedHashSet<Product>  Collection;
    //private final ArrayList<Product> products;
    private final Product[] products;
    private String color;
    public void setLogin(){
        this.login=login;
    }
    HashMap<String, String> colors;
    //    public InformationBox(String message, LinkedHashSet<Product> products, HashMap<String, String> colors, Product p){
//        this.message=message;
//        this.Collection=products;
//        this.colors=colors;
//        this.product=p;
//    }
    public InformationBox(String message, Product[] products, HashMap<String, String> colors, Product p){
        this.message=message;
        this.products=products;
        this.colors=colors;
        this.product=p;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    public Product[] getProductsArray() {
        return products;
    }

    public HashMap<String, String> getColors() {
        return colors;
    }
}
