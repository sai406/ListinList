package saitechcom.listinlist;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    //    private GetMybasketAdapter adapter;
    private GetMyreceptAdapter adapter1;
    ArrayList<ReceptsModel> receptsModelArrayList;
    //    private LinearLayout mOtherMerchantDealsLayout;
    private RecyclerView gv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (RecyclerView) findViewById(R.id.gv);
        gv.setHasFixedSize(true);
        gv.setLayoutManager(new LinearLayoutManager(this));
        getreceptsdata();
    }

    private void getreceptsdata() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
//		String url = "https://gmilink.com/d/AppServices/appcustomerreciept.aspx?cid=727460&mi=2708";
//		String url = "https://gmilink.com/d/AppServices/appcustomerreciept.aspx?cid=";
        String url = "http://kansolvetec.com/androidapps/new.json";

// Request a string response from the provided URL.
//		StringRequest stringRequest = new StringRequest(Request.Method.GET, url+customerId+"&mi="+ApiConstants.MERCHANT_ID,
//				new Response.Listener<String>() {
//
//					@Override
//					public void onResponse(String response) {

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        receptsModelArrayList = new ArrayList<ReceptsModel>();
                        try {
                            // Parsing json array response
                            // loop through each json object
//                            jsonResponse = "";

                            if (response.length() > 0) {

                                for (int i = 0; i < response.length(); i++) {
                                    ReceptsModel receptsModel = new ReceptsModel();
                                    JSONObject gameObj = (JSONObject) response.get(i);
                                    Log.d("abc", "onResponse: " + gameObj);
                                    receptsModel.corderId = gameObj.getString("COrderId");
                                    receptsModel.address = gameObj.getString("Address");
                                    receptsModel.postalCode = gameObj.getString("PostalCode");
                                    receptsModel.amount = gameObj.getString("Amount");
                                    receptsModel.shippingCost = gameObj.getString("ShippingCost");
                                    receptsModel.totalAmount = gameObj.getString("TotalAmount");
                                    receptsModel.orderDate = gameObj.getString("OrderDate");
                                    JSONArray array = gameObj.getJSONArray("Items");
                                    receptsModel.items = array;
                                    receptsModelArrayList.add(receptsModel);

                                }

                                adapter1 = new GetMyreceptAdapter(MainActivity.this, receptsModelArrayList);
                                gv.setVisibility(View.VISIBLE);
                                gv.setAdapter(adapter1);
                            } else {
                                AlertDialog.Builder builder;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                } else {
                                    builder = new AlertDialog.Builder(MainActivity.this);
                                }
                                builder.setTitle("No Receipts")
                                        .setMessage("No Receipts Found")
                                        .setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // continue with delete
                                            }
                                        })
//                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                // do nothing
//                                            }
//                                        })
                                        .setIcon(R.drawable.ic_launcher_background)
                                        .show();
                                Toast.makeText(MainActivity.this, "No Recepts Available", Toast.LENGTH_SHORT).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("recepts", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.hide();

            }
        });

        // Adding request to request queue
        queue.add(req);
    }
}
