package Command;

import Given.Country;
import Given.Person;
import Manager.CollectionManager;

import java.io.Serial;

public class Registration extends Command{
    @Serial
    private static final long serialVersionUID = 18L;
    private String password;
    private String color;
    public String getColor() {
        return color;
    }
    public Registration(String login, String password, String color, String nameO, Float weight, Country nationality){
        name="reg";
        this.password=password;
        this.login = login;
        this.color=color;
        owner = new Person(login, nameO, weight, nationality, color);
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){

    }

}
