package app.com.model;

public class SellerModel {


    public String sName;
    public String sEmail;
    public String sStatus;
    public String sBidItem;

    public SellerModel(String sName,String sEmail,String sStatus, String sBidItem) {
        this.sName = sName;
        this.sEmail = sEmail;
        this.sStatus = sStatus;
        this.sBidItem = sBidItem;
    }

    public String getsName() {
        return sName;
    }

    public String getsEmail() {
        return sEmail;
    }

    public String getsBidItem() {
        return sBidItem;
    }

    public String getsStatus() {
        return sStatus;
    }

    public boolean allocateItemToBid(){

        return true;
    }

}
