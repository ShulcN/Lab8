package Command;

import Given.Person;
import Given.Product;
import Manager.CollectionManager;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.LinkedHashSet;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Product product;
    protected String message;
    protected Person owner;
    protected String name;
    protected String login;
    protected LinkedHashSet<Product> linkedHashSet;
    public Command(){

    }
//    public void DoCommand(){
//
//    }
    public void DoCommand(CollectionManager ls){

    }
//    public void DoCommand(LinkedHashSet ls, boolean b){
//
//    }
    public String getMessage(){
        return message;
    }
    /*public void DoCommand(LinkedHashSet ls, String Id){

    }*/
    public String getLogin() {
        return login;
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public String toString(){
        return name;
    }
    public Product getProduct(){
        return product;
    }
//    public LinkedHashSet<Product> getProducts() {
//        return linkedHashSet;
//    }
}
