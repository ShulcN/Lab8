package Command;

import Manager.CollectionManager;

import java.io.Serial;
/** обновление пароля*/
public class PasswordUpdate extends Command{
    @Serial
    private static final long serialVersionUID = 93L;
    private String oldPassword;
    private String newPassword;
    public PasswordUpdate(String login, String oldPassword, String newPassword){
        this.login=login;
        this.newPassword=newPassword;
        this.oldPassword=oldPassword;

    }
    public void DoCommand(CollectionManager collectionManager){

    }
}
