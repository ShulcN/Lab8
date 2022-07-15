package extractedButtons;

import java.util.Locale;
import java.util.ResourceBundle;

/**метод для кнопки выбора языка*/
public class LanguageButton {
    public static ResourceBundle languageResBun(String choice){
        switch (choice){
            case "Romanian":
                 return ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("rum"));
            case "Spanish(Columbian)":
                return ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("es"));
            case "Croatian":
                return ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("hr"));
            default:
                 return ResourceBundle.getBundle("com.example.lab8.Locale", new Locale("Rus"));
        }
    }
}
