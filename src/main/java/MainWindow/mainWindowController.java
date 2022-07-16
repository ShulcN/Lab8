package MainWindow;

import Command.*;
import Connection.SendCommand;
import Given.*;
import Visualisation.ChangeSizeOfVisualisation;
import Visualisation.DrawFigures;
import com.example.lab8.HelloApplication;
import com.example.lab8.PersonalInfo;
import extractedButtons.LanguageButton;
import extractedButtons.StartAddWindow;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**Главное рабочее окно*/
public class mainWindowController {

    /**список языков*/
    ObservableList<String> languages = FXCollections.observableArrayList("Spanish(Columbian)", "Russian", "Croatian", "Romanian");

    @FXML
    private Button addIfMax;

    @FXML
    private Button add;

    @FXML
    private GridPane gridPane;
    @FXML
    private Button addIfMin;

    @FXML
    private Button ChangeViewButton;
    @FXML
    private Label UserLabel;
    @FXML
    private static final TableColumn<Product, String> CoordinatesColumn = new TableColumn<>();
    @FXML
    private static final TableColumn<Product, String> OwnerColumn = new TableColumn<>();
    @FXML
    private static TableView<Product> Table;

    @FXML
    private static final TableColumn<Product, String> creationDateColumn = new TableColumn<>();

    @FXML
    private static final TableColumn<Product, Long> idColumn = new TableColumn<>();

    @FXML
    private static final TableColumn<Product, String> nameColumn = new TableColumn<>();

    @FXML
    private static final TableColumn<Product, Double> priceColumn = new TableColumn<>();

    @FXML
    private static final TableColumn<Product, String> unitOfMeasureColumn = new TableColumn<>();
    public static Label LabelText;
    @FXML
    private Button returnElements;

    @FXML
    private Button filterStratsWith;

    @FXML
    private Button updateId;

    @FXML
    private ChoiceBox<String> longuage;
    @FXML
    private Button averageOfPrice;

    @FXML
    private Button help;

    @FXML
    private AnchorPane DialogPane;

    @FXML
    private Button history;

    @FXML
    private Button info;

    @FXML
    private Button removeId;

    private static ResourceBundle resources;

    /**область визуализации коллекции*/
    private static AnchorPane productArea;
    /**коллекция содержащая визуализацию каждого объекта коллекции по ключу самого продукта*/
    private static HashMap<Product, Canvas> canvasHashMap;
    /**формат даты для разных языков*/
    private DateFormat dateFormat;
    /**коллекция продуктов*/
    public static LinkedHashSet<Product> products;
    /**поле для определения режима демонстрации на главном окне(Таблица или визуализация)*/
    private boolean viewTable=true;
    private static ComparatorClass comparator = new IdComparator();
    /**поле для сортировки по столбцам*/
    private static boolean order=true;
    private boolean idOrder=true;
    private boolean nameOrder=true;
    private boolean priceOrder=true;
    private boolean coordinatesOrder=true;
    private boolean dateOrder = true;
    private boolean ownerOrder=true;
    private boolean unitOfMeasureOrder=true;


    @FXML
    void initialize() {
        HelloApplication.stage.setResizable(true);
        dateFormat=DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale(getStringBinding("language"), getStringBinding("country")));
        longuage.setItems(languages);
        longuage.setValue("language");
        canvasHashMap=new HashMap<>();
        initFuncButtons();
        Table = new TableView<>();
        initTableColumns();
        UserLabel.setStyle("-fx-background-radius: 40; -fx-background-color: #cfcfcf; -fx-text-fill: "+PersonalInfo.getHexColor());
        LabelText=new Label();
        LabelText.setTextFill(Color.BLACK);
        LabelText.setWrapText(true);
        LabelText.setStyle("-fx-wrap-text: true; -fx-font-family: 'Courier New'; -fx-font-style: italic; -fx-text-alignment: center; -fx-font-size: 12;");
        LabelText.setTextAlignment(TextAlignment.CENTER);
        LabelText.setAlignment(Pos.CENTER);
        DialogPane.getChildren().add(LabelText);
        AnchorPane.setBottomAnchor(LabelText, 2.0);
        AnchorPane.setLeftAnchor(LabelText, 5.0);
        AnchorPane.setRightAnchor(LabelText, 5.0);
        AnchorPane.setTopAnchor(LabelText, 2.0);
        LabelText.setFont(Font.font(12));
        LabelText.setText("");
        LabelText.setVisible(true);
        gridPane.add(Table, 0, 1);
        Table.setLayoutX(0);
        Table.setLayoutY(0);
        GridPane.setMargin(Table, new Insets(0, 5, 5, 5));
        productArea=new AnchorPane();
        gridPane.add(productArea, 0, 1);
        productArea.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 15;");
        GridPane.setMargin(productArea, new Insets(0, 5, 5, 5));
        productArea.setVisible(false);

        HelloApplication.stage.setMinWidth(1044);
        HelloApplication.stage.setMinHeight(570);
        LinkedHashSet<Product> pr = (SendCommand.SendAndGetCommand(new CollectionReturn()));
        products=pr;
        FillTable(pr);
        HelloApplication.stage.heightProperty().addListener((observableValue, oldVal, newVal) -> {
            productArea.getChildren().clear();
            ChangeSizeOfVisualisation.changeHeightOfVisualisation((double) oldVal, (double)newVal, canvasHashMap, productArea);
        });
        HelloApplication.stage.widthProperty().addListener((observableValue, oldVal, newVal) -> {
            productArea.getChildren().clear();
            ChangeSizeOfVisualisation.changeWidthOfVisualisation((double) oldVal,(double)newVal, canvasHashMap, productArea);
        });
        UserLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
            userDataWindow.startWindow(resources)
        );
        Update();
    }
    /**обновление окна при изменении языка*/
    void Update(){
        updateId.setText(getStringBinding("updateId"));
        removeId.setText(getStringBinding("removeId"));
        history.setText(getStringBinding("history"));
        help.setText(getStringBinding("help"));
        averageOfPrice.setText(getStringBinding("averageOfPrice"));
        returnElements.setText(getStringBinding("allElements"));
        filterStratsWith.setText(getStringBinding("filterStartsWith"));
        priceColumn.setText(getStringBinding("price"));
        nameColumn.setText(getStringBinding("nameProduct"));
        creationDateColumn.setText(getStringBinding("creationDate"));
        CoordinatesColumn.setText(getStringBinding("coordinates"));
        OwnerColumn.setText(getStringBinding("owner"));
        unitOfMeasureColumn.setText(getStringBinding("unitOfMeasure"));
        if(viewTable) {
            ChangeViewButton.setText(getStringBinding("GraphView"));
        } else{
            ChangeViewButton.setText(getStringBinding("TableView"));
        }
        UserLabel.setText(getStringBinding("User")+": "+PersonalInfo.getLogin());
    }


    public static ResourceBundle getResources() {
        return resources;
    }


    public static void setResources(ResourceBundle resources) {
        mainWindowController.resources = resources;
    }
    /**метод, возвращающий значение из файла локализации по ключу*/
    public static String getStringBinding(String key) {
        return resources.getString(key);
    }
    /**метод для сортировки таблицы*/
    private static void SortWith(ComparatorClass comparator, boolean Order){
        Stream<Product> stream = products.stream();
        List<Product> productList;
        if(Order) {
            productList = stream.sorted(comparator).collect(Collectors.toList());
        } else {
            productList = stream.sorted(comparator.reversed()).collect(Collectors.toList());
        }
        products.clear();
        products.addAll(productList);
        FillTableWithoutSort(products);
    }
    /**Метод для заполнения таблицы без сортировки*/
    public static void FillTableWithoutSort(LinkedHashSet<Product> products){
        mainWindowController.products=products;
        ObservableList<Product> products1 = FXCollections.observableArrayList();
        products1.addAll(products);
        Table.setItems(products1);
        Table.refresh();
        productArea.getChildren().clear();
        DrawFigures.drawFigures(products, "noth", canvasHashMap, productArea, LabelText);
    }
    /**Метод для заполнения таблицы*/
    public static void FillTable(LinkedHashSet<Product> products) {
        mainWindowController.products = products;
        SortWith(comparator, order);
    }
    /**Анимации кнопок*/
    void ButtonAnim(Node node){
        node.setOpacity(0.8);
        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(ev -> node.setOpacity(1.0));
        pause.play();
    }
    /**присваивание функций кнопок*/
    private void initFuncButtons(){
        info.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(info);
            LabelText.setText(getStringBinding("CollectionInfo") + SendCommand.SendCommand(new Info(PersonalInfo.getLogin())));
        });
        ChangeViewButton.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(ChangeViewButton);
            if(viewTable){
                LabelText.setText(getStringBinding("GraphView"));
                ChangeViewButton.setText(getStringBinding("TableView"));
                Table.setVisible(false);
                productArea.setVisible(true);
                productArea.getChildren().clear();
                DrawFigures.drawFigures(products,  "noth", canvasHashMap, productArea, LabelText);
                viewTable=false;
            } else {
                LabelText.setText(getStringBinding("TableView"));
                ChangeViewButton.setText(getStringBinding("GraphView"));
                productArea.setVisible(false);
                Table.setVisible(true);
                viewTable=true;
            }
        });
        filterStratsWith.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(filterStratsWith);
            filterStartsWithWindow.startWindow(resources);
            if (filterStartsWithWindow.getName() != null) {
                String s = filterStartsWithWindow.getName();
                LinkedHashSet<Product> filtered = SendCommand.SendAndGetCommand(new FilterStartsWithName(s, PersonalInfo.getLogin()));
               // filtered = SendCommand.SendAndGetCommand(new FilterStartsWithName(s));
                try {
                    assert filtered != null;
                    if (!filtered.isEmpty()) {
                        FillTable(filtered);
                        returnElements.setVisible(true);
                        LabelText.setText(getStringBinding("ElementStarts") + " " + s);
                    } else throw new NullPointerException();
                } catch (Exception e) {
                    LabelText.setText(getStringBinding("NoSuchEl"));
                }
            }
        });
        returnElements.setOnAction(e->{
            ButtonAnim(returnElements);
            SendCommand.SendCommand(new Info(PersonalInfo.getLogin()));
            returnElements.setVisible(false);
        });
        longuage.setOnAction(e ->{
            setResources(LanguageButton.languageResBun(longuage.getValue()));
            dateFormat=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,new Locale(getStringBinding("language"), getStringBinding("country")));
            creationDateColumn.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty( dateFormat.format(productStringCellDataFeatures.getValue().getCreationDate())));
            Update();
            FillTable(products);
        });
        add.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(add);
            StartAddWindow.startAddWindow(resources, typeOfWindowAdd.ADD);
        });
        addIfMin.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(addIfMin);
            StartAddWindow.startAddWindow(resources, typeOfWindowAdd.ADD_IF_MIN);
        });
        addIfMax.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(addIfMax);
            StartAddWindow.startAddWindow(resources, typeOfWindowAdd.ADD_IF_MAX);
        });
        history.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(history);
            LabelText.setText(getStringBinding("lastComs")+SendCommand.SendCommand(new History(PersonalInfo.getLogin())));
        });
        averageOfPrice.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(averageOfPrice);
            LabelText.setText(getStringBinding("averageOfPrice")+": "+SendCommand.SendCommand(new AverageOfPrice(PersonalInfo.getLogin())));
        });
        help.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(help);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("helpwindow.fxml"));
            loader.setResources(resources);
            Scene scene=null;
            try {
                scene = new Scene(loader.load(), 800, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("help");
            stage.show();
        });
        removeId.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(removeId);
            Stage stage = new Stage();
            LinkedList<Long> list = Table.getItems().stream().filter(p -> p.getLogin().equals(PersonalInfo.getLogin())).map(Product::getId).collect(Collectors.toCollection(LinkedList::new));
            removeWindow.setIds(list);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("removeWindow.fxml"));
            loader.setResources(resources);
            Scene scene=null;
            try {
                scene = new Scene(loader.load(), 412, 216);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("remove");
            stage.show();
        });
        updateId.addEventHandler(ActionEvent.ACTION, event -> {
            ButtonAnim(updateId);
            addWindow.setAddType((byte) 3);
            LinkedList<Long> list = Table.getItems().stream().filter(p -> p.getLogin().equals(PersonalInfo.getLogin())).map(Product::getId).collect(Collectors.toCollection(LinkedList::new));
            if(list.isEmpty()){
                LabelText.setText(getStringBinding("NoProducts"));
            } else {
                StartAddWindow.startUpdateWindow(resources, null);
            }
        });
    }
    /**инициализация колонок таблицы*/
    private void initTableColumns(){
        idColumn.setText("Id");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        unitOfMeasureColumn.setCellValueFactory(productUnitOfMeasureCellDataFeatures -> new SimpleStringProperty(productUnitOfMeasureCellDataFeatures.getValue().getUnitOfMeasure().toString()));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        CoordinatesColumn.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty(productStringCellDataFeatures.getValue().getStringCoordinates()));
        creationDateColumn.setCellValueFactory(productStringCellDataFeatures -> new SimpleStringProperty( dateFormat.format(productStringCellDataFeatures.getValue().getCreationDate())));
        OwnerColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        idColumn.setReorderable(false);
        nameColumn.setReorderable(false);
        CoordinatesColumn.setReorderable(false);
        creationDateColumn.setReorderable(false);
        priceColumn.setReorderable(false);
        unitOfMeasureColumn.setReorderable(false);
        OwnerColumn.setReorderable(false);
        nameColumn.setSortable(false);
        CoordinatesColumn.setSortable(false);
        OwnerColumn.setSortable(false);
        unitOfMeasureColumn.setSortable(false);
        priceColumn.setSortable(false);
        creationDateColumn.setSortable(false);
        idColumn.setSortable(false);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setEditable(true);
        nameColumn.setOnEditCommit(productStringCellEditEvent -> {
            Product product = productStringCellEditEvent.getRowValue();
            String oldValue = product.getName();
            product.setName(productStringCellEditEvent.getNewValue());
            if (product.getLogin().equals(PersonalInfo.getLogin()) && !productStringCellEditEvent.getNewValue().isEmpty()) {
                products = SendCommand.SendAndGetCommand(new UpdateID(product.getId().toString(), product, PersonalInfo.getLogin()));
            } else {
                product.setName(oldValue);
                LabelText.setText(getStringBinding("NotYours"));
                Table.refresh();
            }
        });
        CoordinatesColumn.setEditable(true);
        CoordinatesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CoordinatesColumn.setOnEditCommit(productStringCellEditEvent -> {
            Product product = productStringCellEditEvent.getRowValue();
            Coordinates oldValue = product.getCoordinates();
            try {
                String[] s = productStringCellEditEvent.getNewValue().split(",");
                product.setCoordinates(new Coordinates(Integer.parseInt(s[0]), Float.parseFloat(s[1])));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (product.getLogin().equals(PersonalInfo.getLogin()) && product.getCoordinates().getX()<=780 && product.getCoordinates().getY()<=260) {
                products = SendCommand.SendAndGetCommand(new UpdateID(product.getId().toString(), product, PersonalInfo.getLogin()));
            } else {
                product.setCoordinates(oldValue);
                LabelText.setText(getStringBinding("NotYours"));
                Table.refresh();
            }
            System.out.println(products);
            FillTable(products);
        });
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceColumn.setEditable(true);
        priceColumn.setOnEditCommit(productDoubleCellEditEvent -> {
            Product product = productDoubleCellEditEvent.getRowValue();
            double oldValue = product.getPrice();
            try {
                product.setPrice(productDoubleCellEditEvent.getNewValue());
            }catch (Exception e){
                e.printStackTrace();
            }
            if (product.getPrice()>10000000){
                LabelText.setText(getStringBinding("CostProblem"));
                product.setPrice(oldValue);
                Table.refresh();
                return;
            }
            if (product.getLogin().equals(PersonalInfo.getLogin())) {
                products = SendCommand.SendAndGetCommand(new UpdateID(product.getId().toString(), product, PersonalInfo.getLogin()));
            } else {
                product.setPrice(oldValue);
                LabelText.setText(getStringBinding("NotYours"));
                Table.refresh();
            }

            FillTable(products);
        });
        unitOfMeasureColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        unitOfMeasureColumn.setEditable(true);
        unitOfMeasureColumn.setOnEditCommit(productStringCellEditEvent -> {
            Product product = productStringCellEditEvent.getRowValue();
            UnitOfMeasure oldValue = product.getUnitOfMeasure();
            try {
                product.setUnitOfMeasure(UnitOfMeasure.valueOf(productStringCellEditEvent.getNewValue()));
            }catch (Exception e){
                e.printStackTrace();
            }
            if (product.getLogin().equals(PersonalInfo.getLogin())) {
                products = SendCommand.SendAndGetCommand(new UpdateID(product.getId().toString(), product, PersonalInfo.getLogin()));
            } else {
                product.setUnitOfMeasure(oldValue);
                LabelText.setText(getStringBinding("NotYours"));
                Table.refresh();
            }
            FillTable(products);
        });
        creationDateColumn.setEditable(true);
        creationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        creationDateColumn.setOnEditCommit(productDateCellEditEvent -> {
            Product product= productDateCellEditEvent.getRowValue();
            Date oldValue = product.getCreationDate();
            try {
                product.setCreationDate(dateFormat.parse(productDateCellEditEvent.getNewValue()));
            }catch (Exception e){
                e.printStackTrace();
            }
            if (product.getLogin().equals(PersonalInfo.getLogin())) {
                products = SendCommand.SendAndGetCommand(new UpdateID(product.getId().toString(), product, PersonalInfo.getLogin()));
            } else {
                product.setCreationDate(oldValue);
                LabelText.setText(getStringBinding("NotYours"));
                Table.refresh();
            }
            FillTable(products);
        });
        Table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if((event.getX()>=idColumn.getWidth() && event.getX()<=nameColumn.getWidth() && event.getY()<24) ){
                comparator=new nameComparator();
                SortWith(comparator, nameOrder);
                order=nameOrder;
                nameOrder=!nameOrder;
            }
            if((event.getX()>=0 && event.getX()<=idColumn.getWidth() && event.getY()<24) ){
                comparator=new IdComparator();
                SortWith(comparator, idOrder);
                order=idOrder;
                idOrder=!idOrder;
            }
            if((event.getX()>=(nameColumn.getWidth()+idColumn.getWidth()) && event.getX()<=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()) && event.getY()<24) ){
                comparator=new coordinatesComparator();
                SortWith(comparator, coordinatesOrder);
                order=coordinatesOrder;
                coordinatesOrder=!coordinatesOrder;
            }
            if((event.getX()>=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()) && event.getX()<=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+creationDateColumn.getWidth()) && event.getY()<24) ){
                comparator=new dataComparator();
                SortWith(comparator, dateOrder);
                order=dateOrder;
                dateOrder=!dateOrder;
            }
            if((event.getX()>=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+creationDateColumn.getWidth()) && event.getX()<=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+priceColumn.getWidth()+creationDateColumn.getWidth()) && event.getY()<24) ){
                comparator=new PriceComparator();
                SortWith(comparator, priceOrder);
                order=priceOrder;
                priceOrder=!priceOrder;
            }
            if((event.getX()>=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+priceColumn.getWidth()+creationDateColumn.getWidth()) && event.getX()<=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+priceColumn.getWidth()+unitOfMeasureColumn.getWidth()+creationDateColumn.getWidth()) && event.getY()<24) ){
                comparator=new UnitOfMeasureComparator();
                SortWith(comparator, unitOfMeasureOrder);
                order=unitOfMeasureOrder;
                unitOfMeasureOrder=!unitOfMeasureOrder;
            }if((event.getX()>=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+priceColumn.getWidth()+unitOfMeasureColumn.getWidth()+creationDateColumn.getWidth()) && event.getX()<=(nameColumn.getWidth()+idColumn.getWidth()+CoordinatesColumn.getWidth()+priceColumn.getWidth()+unitOfMeasureColumn.getWidth()+OwnerColumn.getWidth()+creationDateColumn.getWidth()) && event.getY()<24) ){
                comparator=new OwnerComparator();
                SortWith(comparator, ownerOrder);
                order=ownerOrder;
                ownerOrder=!ownerOrder;
            }
        });
        idColumn.prefWidthProperty().bind(Table.widthProperty().divide(20));
        nameColumn.prefWidthProperty().bind(Table.widthProperty().divide(3));
        CoordinatesColumn.prefWidthProperty().bind(Table.widthProperty().divide(10));
        creationDateColumn.prefWidthProperty().bind(Table.widthProperty().divide(8));
        priceColumn.prefWidthProperty().bind(Table.widthProperty().divide(10));
        unitOfMeasureColumn.prefWidthProperty().bind(Table.widthProperty().divide(10));
        OwnerColumn.prefWidthProperty().bind(Table.widthProperty().divide(5.35));
        idColumn.setResizable(false);
        nameColumn.setResizable(false);
        CoordinatesColumn.setResizable(false);
        creationDateColumn.setResizable(false);
        priceColumn.setResizable(false);
        unitOfMeasureColumn.setResizable(false);
        OwnerColumn.setResizable(false);
        Table.setEditable(true);
        Table.getColumns().addAll(idColumn, nameColumn, CoordinatesColumn, creationDateColumn, priceColumn, unitOfMeasureColumn, OwnerColumn);
    }

}
