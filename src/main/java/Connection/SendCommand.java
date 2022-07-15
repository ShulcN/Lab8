package Connection;

import Command.*;
import Given.Person;
import Given.Product;
import MainWindow.graphic;
import MainWindow.mainWindowController;
import com.example.lab8.HelloApplication;

import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.LinkedHashSet;
/**Класс отправки команд на сервер*/
public class SendCommand {
    private static boolean wait=false;
    /**Метод отправки команды на сервер, который возвращает сообщение этой команды*/
    public static String SendCommand(Command command){
        wait=true;
        try
            {
            ObjectOutputStream objectOutputStream = HelloApplication.getRecThread().getObjectOutputStream();
                objectOutputStream.writeObject(command);
                objectOutputStream.flush();
                while (RecThread.getInformationBox()==null){
                    Thread.yield();
                }
                InformationBox informationBox = RecThread.getInformationBox();
                String mes = informationBox.getMessage();
                try {
                    graphic.setColors(informationBox.getColors());
                    if (informationBox.getColor()!=null){
                        System.out.println(informationBox.getLogin()+ " "+informationBox.getColor());
                        graphic.setColor(informationBox.getLogin(), informationBox.getColor());
                    }
                    mainWindowController.FillTable(new LinkedHashSet<>(Arrays.asList(informationBox.getProductsArray())));

                }catch (NullPointerException ignored){
                }
                RecThread.DeleteInformationBox();
                wait=false;
                System.out.println(mes);
            return mes;
        } catch (Exception e){
            e.printStackTrace();
            HelloApplication.setConnected(false);
            RecThread.DeleteInformationBox();
            //HelloApplication.setSocket(ConnectToServ.Connect());
            e.printStackTrace();
            return "Произошла ошибка во время отправления команды на сервер";
        }
    }
    /**Метод отправки команды на сервер, который возвращает коллекцию из базы данных*/
    public static LinkedHashSet<Product> SendAndGetCommand(Command command){
        wait=true;
        try{
            ObjectOutputStream objectOutputStream = HelloApplication.getRecThread().getObjectOutputStream();
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            System.out.println("Я сенд и отправил команду - "+command.toString());
            while (RecThread.getInformationBox()==null){
                    Thread.yield();
            }
            InformationBox informationBox = RecThread.getInformationBox();
            RecThread.DeleteInformationBox();
            System.out.println(" коробака:"+informationBox.hashCode()+" с пержметом "+informationBox.getMessage()+";  "+informationBox.getProduct()+"; коллекция "+informationBox.getProductsArray().length);
            if (informationBox.getColor()!=null){
                graphic.setColor(informationBox.getLogin(), informationBox.getColor());
            }
            graphic.setColors(informationBox.getColors());
            wait=false;
            LinkedHashSet<Product> products = new LinkedHashSet<>(Arrays.asList(informationBox.getProductsArray()));
            try {
                mainWindowController.LabelText.setText(mainWindowController.getStringBinding(informationBox.getMessage()));
            } catch (Exception ignored){
                System.out.println(informationBox.getMessage());
            }
            return products;
        } catch (Exception e){
            e.printStackTrace();
            HelloApplication.setConnected(false);
            RecThread.DeleteInformationBox();
            return null;
        }
    }
    /**Метод отправки команды на сервер, который возвращает персональную информацию клиента этой команды*/
    public static Person LogIn(Command command){
        wait=true;
        try{
            ObjectOutputStream objectOutputStream = HelloApplication.getRecThread().getObjectOutputStream();
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            System.out.println("Я сенд и отправил команду - "+command.toString());
            while (RecThread.getInformationBox()==null){
                Thread.yield();
            }
            InformationBox informationBox = RecThread.getInformationBox();
            RecThread.DeleteInformationBox();
            System.out.println(" коробака:"+informationBox.hashCode()+" с пержметом "+informationBox.getMessage()+";  "+informationBox.getProduct()+"; коллекция "+informationBox.getProductsArray().length);
            Person owner = informationBox.getOwner();
            if (informationBox.getColor()!=null){
                System.out.println(informationBox.getLogin()+ " "+informationBox.getColor());
                graphic.setColor(informationBox.getLogin(), informationBox.getColor());
            }
            graphic.setColors(informationBox.getColors());
            wait=false;
            try {
                mainWindowController.LabelText.setText(mainWindowController.getStringBinding(informationBox.getMessage()));
            } catch (Exception ignored){

            }
            return owner;
        } catch (Exception e){
            e.printStackTrace();
            RecThread.DeleteInformationBox();
            HelloApplication.setConnected(false);
            return null;
        }
    }
    public static boolean isWait() {
        return wait;
    }
}
