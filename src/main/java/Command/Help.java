package Command;

import Manager.CollectionManager;

public class Help extends Command{
    private static final long serialVersionUID = 6L;
    /**вывод справки по доступным командам */
    public Help(String login){
        this.login=login;
        name="help";

    }
    public void DoCommand(CollectionManager collectionManager){

    }
}
