package MainWindow;


import Command.UpdateID;
import Connection.SendCommand;
import Given.Coordinates;
import Given.Product;
import Given.ProductType;
import Given.UnitOfMeasure;
import com.example.lab8.PersonalInfo;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class updateWindowController {
    @FXML
    private ChoiceBox<Long> ID;


    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<String> kind;

    @FXML
    private TextField nameProduct;

    @FXML
    private Spinner<Double> priceField;

    @FXML
    private ChoiceBox<String> unitOfMeasureField;

    @FXML
    private Spinner<Integer> xField;

    @FXML
    private Spinner<Double> yField;

    private Product product;
    private static Long preId;
    ObservableList<String> unitOfMeasure = FXCollections.observableArrayList("METERS", "CENTIMETERS", "MILLIGRAMS", "GRAMS");
    ObservableList<Long> ids;
    ObservableList<String> types;
    //ObservableList<ProductType> types = FXCollections.observableArrayList(ProductType.DEFAULT, ProductType.ELECTRONIC, ProductType.FOODSTUFF, ProductType.FURNITURE, ProductType.INSTRUMENTS);
    @FXML
    void initialize() {
        types=FXCollections.observableArrayList("1)"+ mainWindowController.getStringBinding(ProductType.DEFAULT.getDescription()),
               "2)"+ mainWindowController.getStringBinding(ProductType.INSTRUMENTS.getDescription()),
                "3)"+ mainWindowController.getStringBinding(ProductType.ELECTRONIC.getDescription()),
                "4)"+ mainWindowController.getStringBinding(ProductType.FOODSTUFF.getDescription()),
                "5)"+ mainWindowController.getStringBinding(ProductType.FURNITURE.getDescription()));
        unitOfMeasureField.setItems(unitOfMeasure);
        LinkedHashSet<Long> ids_2 = mainWindowController.products.stream().filter(p -> p.getLogin().equals(PersonalInfo.getLogin())).map(Product::getId).collect(Collectors.toCollection(LinkedHashSet::new));
        ids= FXCollections.observableArrayList(ids_2);
        ID.setItems(ids);
        kind.setItems(types);
        ID.addEventHandler(ActionEvent.ACTION, e ->
            fillTheFields());
        if (preId!=null){
            ID.setValue(preId);
            fillTheFields();
            preId=null;
        }
    }
    private void fillTheFields(){
        Product p = mainWindowController.products.stream().filter(pr -> pr.getId().equals(ID.getValue())).findFirst().orElse(null);
        if (p != null) {
            product=p;
            xField.getValueFactory().setValue(p.getCoordinates().getX());
            yField.getValueFactory().setValue(p.getCoordinates().getY().doubleValue());
            priceField.getValueFactory().setValue(p.getPrice());
            nameProduct.setText(p.getName());
            unitOfMeasureField.setValue(p.getUnitOfMeasure().toString());
            date.setValue(p.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            kind.setValue(mainWindowController.getStringBinding(p.getType().getDescription()));
        }
    }
    public static void setPreId(Long preId) {
        updateWindowController.preId = preId;
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
        }else if (kind.getValue().contains("1")) {
            return ProductType.DEFAULT;
        } else  {
            return product.getType();
        }
    }

    @FXML
    public void UpdateBut(){
        if (ID.getValue()==null)return;
        UnitOfMeasure unitOfMeasure=UnitOfMeasure.valueOf(unitOfMeasureField.getValue());
        int x = xField.getValue();
        if (x>785){
            Shake(xField);
            return;
        }
        float y = yField.getValue().floatValue();
        if (y>265){
            Shake(yField);
            return;
        }
        double price = priceField.getValue();
        String name = nameProduct.getText();
        if(name==null || name.isEmpty()){
            Shake(nameProduct);
            return;
        }
        Date creationDate = java.util.Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Product p = new Product(ID.getValue(), name, new Coordinates(x, y), price, creationDate, unitOfMeasure, productType(),PersonalInfo.getLogin());
        LinkedHashSet<Product> linkedHashSet = SendCommand.SendAndGetCommand(new UpdateID(ID.getValue().toString(), p, PersonalInfo.getLogin()));
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
    @FXML
    public void cancel(){
        ((Stage)xField.getScene().getWindow()).close();
    }
}
