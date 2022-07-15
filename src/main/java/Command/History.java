package Command;

import Manager.CollectionManager;

import java.util.ArrayList;
import java.util.Iterator;

public class History extends Command{
    private static final long serialVersionUID =11L;
    public History(String login){
        this.login=login;
        name="history";
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){

    }
}
