package SignInWindow;

import Command.Command;
import Command.Login;
import Connection.SendCommand;
import Given.Person;
import com.example.lab8.PersonalInfo;

/**Класс с функцией кнопки авторизации*/
public class SignInBut {
    public static String ClickOnButton(String login, String password){
        String mes;
        if (login.isEmpty() || password.isEmpty()) return  "EmptyPassword";
        PersonalInfo.setLogin(login);
        Command command1 = new Login(login, password);
        mes = SendCommand.SendCommand(command1);
        if (mes.contains("Auth")) {
            Login command = new Login(login, password);
            try {
                Person owner = SendCommand.LogIn(command);
                PersonalInfo.setColor(owner.getColor());
                javafx.scene.paint.Color color = javafx.scene.paint.Color.valueOf(owner.getColor());
                PersonalInfo.setHexColor(String.format( "#%02X%02X%02X",
                        (int)( color.getRed() * 255 ),
                        (int)( color.getGreen() * 255 ),
                        (int)( color.getBlue() * 255 ) ));
                PersonalInfo.setName(owner.getName());
                PersonalInfo.setNationality(owner.getNationality());
                PersonalInfo.setWeight(owner.getWeight());
            } catch (NullPointerException ignored) {

            }
        }
        return mes;
    }
}
