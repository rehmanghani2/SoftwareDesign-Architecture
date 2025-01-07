package app.com.view;

import app.com.model.BidItemCart;
import app.com.model.ItemModel;
import app.com.model.SellerModel;

public class ShowDetails {
 BidItemCart bidItemCart;
    public int item_id;


    public ShowDetails(BidItemCart bidItemCart) {
        this.bidItemCart = bidItemCart;
    }

    public int getItem_id() {
        return item_id;
    }



    public void displayDetails(BidItemCart cart){
        System.out.println("---------Item Aucted Details-------");
        System.out.println("Item name : "+cart.getItemName());
        System.out.println("Item category: "+cart.getItemCategory());
        System.out.println("Seller name: "+cart.getSellerName());
        System.out.println("Seller email: "+cart.getSellerEmail());


    }

}
