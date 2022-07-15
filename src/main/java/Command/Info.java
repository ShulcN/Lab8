package Command;


import Manager.CollectionManager;

import java.util.LinkedHashSet;

public class Info extends  Command{
    private static final long serialVersionUID = 7L;
    /**Метод вывода информации о коллекции*/
    public Info(String login){
        this.login=login;
        name="info";
    }
    @Override
    public void DoCommand(CollectionManager linkedHashSet){

    }
}
