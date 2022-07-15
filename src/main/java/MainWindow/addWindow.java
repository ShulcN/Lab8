package MainWindow;

import Command.Add;
import Command.AddIfMinOrMax;
import Connection.SendCommand;
import Given.Coordinates;
import Given.Product;
import Given.ProductType;
import Given.UnitOfMeasure;
import com.example.lab8.PersonalInfo;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.Date;
import java.util.LinkedHashSet;


public class addWindow {
    private Product product;
    private final String[] fields = new String[13];

    @FXML
    private TextField nameProduct;

    @FXML
    private Spinner<Double> priceField;

    @FXML
    private ChoiceBox<String> unitOfMeasureField;

    @FXML
    private ChoiceBox<String> kind;
    @FXML
    private Spinner<Integer> xField;

    @FXML
    private Spinner<Double> yField;


    ObservableList<String> unitOfMeasure = FXCollections.observableArrayList("METERS", "CENTIMETERS", "MILLIGRAMS", "GRAMS");
    ObservableList<String> types;

    public static void setAddType(byte addType) {
        addWindow.addType = addType;
    }

    @FXML
    void initialize() {
        unitOfMeasureField.setItems(unitOfMeasure);
        types=FXCollections.observableArrayList("1)"+ mainWindowController.getStringBinding(ProductType.DEFAULT.getDescription()),
                "2)"+ mainWindowController.getStringBinding(ProductType.INSTRUMENTS.getDescription()),
                "3)"+ mainWindowController.getStringBinding(ProductType.ELECTRONIC.getDescription()),
                "4)"+ mainWindowController.getStringBinding(ProductType.FOODSTUFF.getDescription()),
                "5)"+ mainWindowController.getStringBinding(ProductType.FURNITURE.getDescription()));
        kind.setItems(types);
    }
    private ProductType productType(){
        if (kind.getValue().contains("2")){
            return ProductType.INSTRUMENTS;
        } else if (kind.getValue().contains("3")){
            return ProductType.ELECTRONIC;
        } else if (kind.getValue().contains("4")){
            return ProductType.FOODSTUFF;
        } else  if (kind.getValue().contains("5")){
            return ProductType.FURNITURE;
        }else {
            return ProductType.DEFAULT;
        }
    }
    public Product getProduct(){
        return product;
    }
    private static byte addType;
    public void fillStrings(){
        Stage stage = ((Stage)xField.getScene().getWindow());
        fields[0]=nameProduct.getText();
        if(fields[0]==null || fields[0].isEmpty()){
            Shake(nameProduct);
            return;
        }
        fields[1]=xField.getValue().toString();
        try {
            int t = Integer.parseInt(fields[1]);
        if(fields[1].isEmpty() || t>855) throw new Exception();
        } catch (Exception e){
            Shake(xField);
            return;
        }
        fields[2]=yField.getValue().toString();
        try {
            float t = Float.parseFloat(fields[2]);
            if(fields[2]==null || t>337)throw new Exception();
        } catch (Exception e){
            Shake(yField);
            return;
        }
        fields[3]=priceField.getValue().toString();
        try {
            double t = Double.parseDouble(fields[3]);
        if(fields[3]==null || priceField.getValue()==0)throw  new Exception();
        }catch (Exception e){
            Shake(priceField);
            return;
        }
        fields[4]=unitOfMeasureField.getValue();
        if (fields[4]==null){
            Shake(unitOfMeasureField);
            return;
        }
        if (kind.getValue()==null){
            Shake(kind);
            return;
        }
        product=new Product(0L,fields[0],new Coordinates(Integer.parseInt(fields[1]), Float.parseFloat(fields[2])), Double.parseDouble(fields[3]), new Date(),UnitOfMeasure.valueOf(fields[4]), productType(), PersonalInfo.getLogin());
        stage.close();
        LinkedHashSet<Product> linkedHashSet=null;
        if (addType==0){
        linkedHashSet = SendCommand.SendAndGetCommand(new Add(product));
        } else {
            if (addType==1){
                linkedHashSet = SendCommand.SendAndGetCommand(new AddIfMinOrMax(true, product));
            } else if (addType==2) {
                linkedHashSet = SendCommand.SendAndGetCommand(new AddIfMinOrMax(false, product));
            }
        }
        mainWindowController.FillTable(linkedHashSet);
        cancel();
    }

    void Shake(Node node){
        TranslateTransition tt = new TranslateTransition(Duration.millis(100), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(4);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }

    public void cancel(){
        ((Stage)xField.getScene().getWindow()).close();
    }
}
