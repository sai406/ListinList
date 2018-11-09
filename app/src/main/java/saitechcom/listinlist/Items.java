package saitechcom.listinlist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Items implements Serializable {

    @SerializedName("Item")
    public String item;
    @SerializedName("Price")
    public String price;
    @SerializedName("Quantity")
    public String quantity;

    @SerializedName("Amount")
    public String amount;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
