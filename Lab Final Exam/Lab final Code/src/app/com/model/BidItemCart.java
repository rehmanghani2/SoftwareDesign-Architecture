package app.com.model;

public class BidItemCart {
    String sellerName;
    String itemName;
    String sellerEmail;
    String itemCategory;

    public BidItemCart(String sellerName, String itemName, String sellerEmail, String itemCategory) {
        this.sellerName = sellerName;
        this.itemName = itemName;
        this.sellerEmail = sellerEmail;
        this.itemCategory = itemCategory;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public String getItemCategory() {
        return itemCategory;
    }
}
