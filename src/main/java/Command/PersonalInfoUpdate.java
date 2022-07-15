package Command;

import Given.Country;
import Manager.CollectionManager;

import java.io.Serial;

public class PersonalInfoUpdate extends Command{
    @Serial
    private static final long serialVersionUID = 89L;
    private String nameUser;
    private Country nationality;
    private Float weight;
    public PersonalInfoUpdate(String login, String nameUser, Country nationality, Float weight){
        this.login=login;
        this.nameUser=nameUser;
        this.nationality=nationality;
        this.weight = weight;
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){

    }
}
