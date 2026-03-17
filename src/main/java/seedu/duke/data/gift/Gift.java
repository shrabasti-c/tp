package seedu.duke.data.gift;

public class Gift {
    private final  String giftName;
    private  boolean isDelivered;

    public Gift(String giftName){
        this.giftName= giftName;
        this.isDelivered= false;
    }

    public String getGiftName(){
        return giftName;
    }
    public boolean isDelivered(){
        return isDelivered;
    }
    public void setDelivered(boolean delivered){
        this.isDelivered=delivered;
    }
    @Override
    public String toString(){
        return isDelivered ? "[Delivered] " : "[Pending]";
    }
}

