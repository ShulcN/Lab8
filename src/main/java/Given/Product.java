package Given;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {
    @Serial
    private static final long serialVersionUID = 101L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Person owner; //Поле не может быть null
    private String login="";
    private final ProductType type;

    public Product(Long id, String name, Coordinates coordinates, double price, Date date, UnitOfMeasure unitOfMeasure, ProductType type,String login){
        this.coordinates = coordinates;
        this.name = name;
        this.type=type;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.id = id;
        this.creationDate = date;
        this.login =login;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Person getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
    @Override
    public String toString(){
        return "ID: "+this.getId()+"; Название продукта "+this.getName()+
                "; логин владельца "+login+"; Цена "+this.getPrice()+"; Дата создания "+this.creationDate+"; Еденица Измерения "+this.getUnitOfMeasure()+"; координаты "+this.getCoordinates();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product product))return false;
        return Objects.equals(this.id, product.getId());
    }
    @Override
    public int compareTo(Product p){
        return (int) (this.id - p.id);
    }
    //    public String ForInsert(){
//        return "'"+name+"', ('"+coordinates.getX()+"', '"+coordinates.getY()+"'),'"+creationDate+"' ,'"+price+"', '"+unitOfMeasure+"', ('"+owner.getName()+"', '"+owner.getWeight()+"', '"+owner.EyeColorToStr()+"', '"+owner.getHairColor()+"', '"+owner.getNationality()+"', ('"+owner.getLocation().getX()+"', '"+owner.getLocation().getY()+"', '"+owner.getLocation().getZ()+"')), '"+login+"'";
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ProductType getType() {
        return type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getStringCoordinates(){
        return coordinates.getX()+","+coordinates.getY();
    }
    public String getLogin() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }
}