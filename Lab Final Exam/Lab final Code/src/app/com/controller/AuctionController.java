package app.com.controller;

import app.com.model.BidItemCart;
import app.com.model.ItemModel;
import app.com.model.SellerModel;
import app.com.view.ShowDetails;

public class AuctionController {
    public SellerModel sellerModel;
    public ItemModel itemModel;
    public BidItemCart bidItemCart;
    public ShowDetails showDetails;

    public AuctionController(SellerModel sellerModel, ItemModel itemModel,
                             BidItemCart bidItemCart, ShowDetails showDetails){
        this.itemModel = itemModel;
        this.sellerModel = sellerModel;
        this.bidItemCart = bidItemCart;
        this.showDetails = showDetails;
    }


    public SellerModel setSellerData(SellerModel sellerModel){
        this.sellerModel = sellerModel;
        return sellerModel;
    }

    public ItemModel setItemData(ItemModel itemModel){
        this.itemModel = itemModel;
        return itemModel;
    }

    public BidItemCart setItemCartData(BidItemCart bidItemCart){
        this.bidItemCart = bidItemCart;
        return bidItemCart;
    }

    public boolean checkSellerStatus(String status){
         if(status.equals("Login")){
             return true;
         } else {
             return false;
         }
    }

    public boolean checkItemStatus(String status){
        if(status.equals("OnBid")){
            return true;
        } else {
            return false;
        }
    }

    public String setAuctionCartData(BidItemCart bidItemCart){
        if(checkItemStatus(this.sellerModel.sStatus)){
            this.bidItemCart = bidItemCart;
            return ("Seller is Login \n Item is added to cart");
        } else {
            return ("Seller is not login");
        }
    }

    public void showData(){
        showDetails.displayDetails(this.bidItemCart);
    }

}
