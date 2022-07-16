package Given;

public enum ProductType {
//    INSTRUMENTS("instruments", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\instruments.png"),
//    DEFAULT("default", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\box.png"),
//    FOODSTUFF("foodstuff", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\products.png"),
//    ELECTRONIC("electronic", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\technology.png"),
//    FURNITURE("furniture", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\furniture.png")
//    INSTRUMENTS("instruments", "/resources/com/example/lab8/icons/instruments.png"),
//    DEFAULT("default", "/resources/com/example/lab8/icons/box.png"),
//    FOODSTUFF("foodstuff", "/resources/com/example/lab8/icons/products.png"),
//    ELECTRONIC("electronic", "/resources/com/example/lab8/icons/technology.png"),
//    FURNITURE("furniture", "/resources/com/example/lab8/icons/furniture.png")
//    INSTRUMENTS("instruments", "instruments.png"),
//    DEFAULT("default", "box.png"),
//    FOODSTUFF("foodstuff", "products.png"),
//    ELECTRONIC("electronic", "technology.png"),
//    FURNITURE("furniture", "furniture.png")
    INSTRUMENTS("instruments", "/com/example/lab8/icons/instruments.png"),
    DEFAULT("default", "/com/example/lab8/icons/box.png"),
    FOODSTUFF("foodstuff", "/com/example/lab8/icons/products.png"),
    ELECTRONIC("electronic", "/com/example/lab8/icons/technology.png"),
    FURNITURE("furniture", "/com/example/lab8/icons/furniture.png")
    ;
    String description;
    String imgPath;
    ProductType(String s, String imgPath) {
        description=s;
        this.imgPath=imgPath;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }
}
