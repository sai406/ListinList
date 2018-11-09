package saitechcom.listinlist;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReceptsModel implements Serializable {

    @SerializedName("COrderId")
    public String corderId;

    @SerializedName("Address")
    public String address;

    @SerializedName("PostalCode")
    public String postalCode;

    @SerializedName("Amount")
    public String amount;

    @SerializedName("ShippingCost")
    public String shippingCost;

    @SerializedName("TotalAmount")
    public String totalAmount;
    @SerializedName("OrderDate")
    public String orderDate;
    @SerializedName("Items")
    public JSONArray items;
}
