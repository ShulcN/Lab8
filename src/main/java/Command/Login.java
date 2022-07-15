package Command;


import Manager.CollectionManager;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends Command{
    private static final long serialVersionUID = 17L;
    private String password;
    public Login(String login, String password){
        this.password = password;
        this.login = login;
    }
    @Override
    public void DoCommand(CollectionManager collectionManager){

    }
}
