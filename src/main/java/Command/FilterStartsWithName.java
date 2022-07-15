package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class FilterStartsWithName extends Command{
    private static final long serialVersionUID = 10L;
    private String s;
    public FilterStartsWithName(String s, String login){
        name="filter_starts_with_name";
        this.s=s;
        this.login=login;
    }
    /**метод для вывода в консоль товаров, названия которых начинаются с переданной строки*/
    @Override
    public void DoCommand(CollectionManager collection){

    }
}
