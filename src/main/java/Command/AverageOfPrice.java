package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class AverageOfPrice extends Command{
    private static final long serialVersionUID = 11L;
    /**Вывод средней цены всех товаров в коллекции*/
    public AverageOfPrice(String login){
        this.login=login;
        name="average_of_price";
    }
    @Override
    public void DoCommand(CollectionManager collection){

    }
}
