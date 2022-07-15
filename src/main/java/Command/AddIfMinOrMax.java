package Command;

import Given.*;
import Manager.CollectionManager;

import java.io.Serial;


public class AddIfMinOrMax extends Command{
    @Serial
    private static final long serialVersionUID = 12L;
    private final boolean MOM;
    public AddIfMinOrMax(boolean MOM, String login){
        name="add_if_min";
        this.MOM = MOM;
        //product = new Product(fields, login);
        super.login = login;
    }
    public AddIfMinOrMax(boolean MOM, Product p){
        if (MOM){
        name="add_if_min";} else name="add_if_max";
        this.MOM=MOM;
        this.product=p;
        login= product.getLogin();

    }
    /** метод добавления элемента в коллекцию, при определенной цене*/
    public void DoCommand(CollectionManager collection){

    }
}
