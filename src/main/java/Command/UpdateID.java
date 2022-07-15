package Command;

import Given.Product;
import Manager.CollectionManager;

import java.io.Serial;

public class UpdateID extends Command{

    @Serial
    private static final long serialVersionUID = 4L;
    public UpdateID(String Id,Product pr, String login){
        name="update";
        super.login=login;
        long id;
        try {
            id = Long.parseLong(Id);
            if (id<1)throw new Exception();
        } catch (Exception e ){
            System.out.println("введенное Id некорректно");
            return;
        }
        product=pr;
        product.setId(id);
    }
    /**Замена элемента коллекции по его id*/
    @Override
    public void DoCommand(CollectionManager collection){

    }
}
