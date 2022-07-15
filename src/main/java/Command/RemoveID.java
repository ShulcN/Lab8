package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class RemoveID extends Command{
    private static final long serialVersionUID = 5L;
    private long id;
    public RemoveID(String id, String login) {
        name="remove_by_id";
        super.login=login;
        try {
            this.id = Long.valueOf(id);
        } catch (Exception e){

        }
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){

    }
}
