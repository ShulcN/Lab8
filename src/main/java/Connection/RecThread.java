package Connection;

import Command.InformationBox;
import Given.*;
import MainWindow.graphic;
import MainWindow.mainWindowController;
import javafx.application.Platform;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashSet;


/**Поток для получения ответа от сервера*/
public class RecThread extends Thread{
    public static InformationBox informationBox;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    public RecThread(Socket socket) throws IOException {
        objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectInputStream=new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    @Override
    public void run(){
        try {
            while(true){
                try {
                    informationBox = (InformationBox) objectInputStream.readObject();

                }catch (EOFException e){
                    System.out.println("Ошибка при получении");
                    continue;
                }

                try{
                    if(!SendCommand.isWait()){
                        if (informationBox.getProduct()!=null){
                            System.out.println("Я не ждал но получил это "+" коробака:"+informationBox.hashCode()+" с пержметом "+informationBox.getMessage()+";  "+informationBox.getProduct()+"; коллекция "+informationBox.getProductsArray().length);
                            LinkedHashSet<Product> products = new LinkedHashSet<>(Arrays.asList(informationBox.getProductsArray()));
                            if (informationBox.getColor()!=null){
                                graphic.setColor(informationBox.getLogin(), informationBox.getColor());
                            }

                            graphic.setColors(informationBox.getColors());
                            try {
                                 Platform.runLater(() -> {
                                     mainWindowController.FillTable(products);
                                 });
                            } catch (Exception e){
                                System.out.println("ошибка при отрисовке");
                                e.printStackTrace();
                            }
                        }
                        graphic.setColors(informationBox.getColors());
                        informationBox=null;
                    }} catch (NullPointerException e){
                    System.out.println(informationBox+" bebebe");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static InformationBox getInformationBox() {
        return informationBox;
    }
    public static void DeleteInformationBox(){
        informationBox=null;
    }
}
