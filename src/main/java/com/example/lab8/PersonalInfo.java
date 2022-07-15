package com.example.lab8;

import Given.Country;
/**Класс с персональной информацией клиента*/
public class PersonalInfo {
    private static String login;
    private static Float weight=null;
    private static Country nationality;
    private static String name;
    private static String color;
    private static String hexColor;

    public static String getLogin() {
        return login;
    }

    public static Float getWeight() {
        return weight;
    }

    public static Country getNationality() {
        return nationality;
    }

    public static String getHexColor() {
        return hexColor;
    }

    public static void setHexColor(String hexColor) {
        PersonalInfo.hexColor = hexColor;
    }

    public static String getName() {
        return name;
    }

    public static String getColor() {
        return color;
    }

    public static void setLogin(String login) {
        PersonalInfo.login = login;
    }

    public static void setWeight(Float weight) {
        PersonalInfo.weight = weight;
    }

    public static void setNationality(Country nationality) {
        PersonalInfo.nationality = nationality;
    }

    public static void setName(String name) {
        PersonalInfo.name = name;
    }

    public static void setColor(String color) {
        PersonalInfo.color = color;
    }
}
