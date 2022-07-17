package Given;

public enum ProductType {
//    INSTRUMENTS("instruments", "instruments.png"),
//    DEFAULT("default", "box.png"),
//    FOODSTUFF("foodstuff", "products.png"),
//    ELECTRONIC("electronic", "technology.png"),
//    FURNITURE("furniture", "furniture.png")
    INSTRUMENTS("instruments", "/com/example/lab8/Instruments.png"),
    DEFAULT("default", "/com/example/lab8/box.png"),
    FOODSTUFF("foodstuff", "/com/example/lab8/products.png"),
    ELECTRONIC("electronic", "/com/example/lab8/technology.png"),
    FURNITURE("furniture", "/com/example/lab8/furniture.png")
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
