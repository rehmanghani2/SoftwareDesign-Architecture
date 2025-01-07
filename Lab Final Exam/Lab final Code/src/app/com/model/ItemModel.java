package app.com.model;

public class ItemModel {
    public int id;
    public String name;
    public String status;
    public String category;
    public ItemModel(int id, String name, String status, String category){
        this.id = id;
        this.name = name ;
        this.status = status;
        this.category = category;
    }

    public int getId(){
        return this.id;
    }
    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
}
