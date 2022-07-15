package Given;


import javafx.scene.paint.Color;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 100L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Float weight=null; //Поле может быть null, Значение поля должно быть больше 0
    private Country nationality; //Поле не может быть null
    private String login;
    private String color;

    public Person(String login, String name, Float weight, Country nationality, String color){
        this.login=login;
        this.color=color;
        this.name=name;
        this.nationality=nationality;
        try {
            if(weight <=0){
                this.weight=weight;
            }
        }
        catch (NullPointerException e){
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }


    public Float getWeight() {
        return weight;
    }
    public String WeightToStr(){
        try {
            return weight.toString();}
        catch (NullPointerException e){return "";}
    }

    public Country getNationality() {
        return nationality;
    }

    public String getLogin() {
        return login;
    }


    @Override
    public String toString(){
        return name;
    }
    public String getInfo(){
        return " Имя: "+getName()+" вес: "+WeightToStr()+" национальность: "+nationality;
    }
}
