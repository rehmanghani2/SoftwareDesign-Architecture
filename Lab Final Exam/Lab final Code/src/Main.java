import app.com.controller.AuctionController;
import app.com.model.BidItemCart;
import app.com.model.ItemModel;
import app.com.model.SellerModel;
import app.com.view.ShowDetails;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SellerModel sm = new SellerModel("Ali","ali@email.com","Login","Car");

        ItemModel im = new ItemModel(1, "Car", "OnBid","Vehicles");
        BidItemCart cart = new BidItemCart(sm.sName, im.name, sm.sEmail, im.category);
        ShowDetails sd = new ShowDetails(cart);

        AuctionController ac = new AuctionController(sm,im,cart,sd);

        ac.showData();

        }
    }
