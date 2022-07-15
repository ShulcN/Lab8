package Command;

import Given.*;
import Manager.CollectionManager;

import java.io.Serial;


public class Add extends Command {
    @Serial
    private static final long serialVersionUID = 2L;
    public Add(String login){
        name="add";
        String[] fields;
        //super.product = new Product(fields, login);
        this.login = login;
    }
    public Add(String[] fields)
    {
        name="add";
        //product=new Product(fields, login);
    }
    public Add(Product product){
        name="add";
        login= product.getLogin();
        this.product =product;
    }
    /**Метод добавления элемента в коллекцию*/
    @Override
    public void DoCommand(CollectionManager collection){

    }
}
