package Connection;

import com.example.lab8.HelloApplication;

import java.net.Socket;
/**Класс для подключения к серверу*/
public class ConnectToServ {
    public synchronized static Socket Connect(){
        try {
            Socket socket = new Socket("localhost", 444);

            System.out.println(socket);
            HelloApplication.setConnected(true);
            return socket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
