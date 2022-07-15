package Given;

public enum ProductType {
    INSTRUMENTS("instruments", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\instruments.png"),
    DEFAULT("default", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\box.png"),
    FOODSTUFF("foodstuff", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\products.png"),
    ELECTRONIC("electronic", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\technology.png"),
    FURNITURE("furniture", "C:\\Users\\Degra\\IdeaProjects\\Lab8\\src\\main\\resources\\com\\example\\lab8\\icons\\furniture.png")
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
