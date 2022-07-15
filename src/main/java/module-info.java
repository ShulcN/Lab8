module com.example.lab8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab8 to javafx.fxml;
    exports com.example.lab8;
    exports SignInWindow;
    opens SignInWindow to javafx.fxml;
    exports SignUpWindow;
    opens SignUpWindow to javafx.fxml;
    exports MainWindow;
    opens MainWindow to javafx.fxml;
    exports Given;
    opens Given to javafx.base;

}