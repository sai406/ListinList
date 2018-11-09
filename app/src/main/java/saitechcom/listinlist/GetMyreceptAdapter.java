package saitechcom.listinlist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetMyreceptAdapter extends RecyclerView.Adapter<GetMyreceptAdapter.ProductViewHolder> {

    private Activity mContext;
    private LayoutInflater li;
    private List<ReceptsModel> receptsModelArrayList = new ArrayList<ReceptsModel>();
    private JSONArray receptsModelArrayList1 = new JSONArray();
    ArrayList<Items> itemsarray = new ArrayList<Items>();
    View view;

    @SuppressWarnings("static-access")
    public GetMyreceptAdapter(Activity context, List<ReceptsModel> list) {
        this.mContext = context;
        li = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        receptsModelArrayList = list;
        Log.d("LIST", "GetMyreceptAdapter: " + list);
    }

//    @Override
//    public int getCount() {
//        return (receptsModelArrayList != null && receptsModelArrayList.size() > 0) ? receptsModelArrayList.size() : 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.recepts, null);
        return new GetMyreceptAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.orderid.setText("Invoice #" + receptsModelArrayList.get(position).corderId);
        holder.date.setText("Order Date : " + receptsModelArrayList.get(position).orderDate);
        holder.amount.setText("Amount : " + receptsModelArrayList.get(position).amount);
        holder.shipingamount.setText("ShippingCost : " + receptsModelArrayList.get(position).shippingCost);
        holder.total.setText("TotalAmount : " + receptsModelArrayList.get(position).totalAmount);
        holder.address.setText("Address : " + receptsModelArrayList.get(position).address);

        receptsModelArrayList1 = receptsModelArrayList.get(position).items;
        Log.d("receptsModelArrayList1", "getView: " + receptsModelArrayList1);
        if (!receptsModelArrayList1.equals("")) {

            LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
            layout.removeAllViews();
            for (int i = 0; i < receptsModelArrayList1.length(); i++) {
                JSONObject gameObj = null;
                try {
//                    v.setTag(holder);
                    gameObj = (JSONObject) receptsModelArrayList1.get(i);
                    Log.d("1", "getView: " + gameObj.getString("Item"));
                    Log.d("2", "getView: " + gameObj.getString("Price"));
                    Log.d("3", "getView: " + gameObj.getString("Quantity"));
                    Log.d("4", "getView: " + gameObj.getString("Amount"));
                    View child = li.inflate(R.layout.receptitemrow, null);
                    layout.addView(child);
                    TextView ritemname = (TextView) child.findViewById(R.id.itemname);
                    TextView rquantity = (TextView) child.findViewById(R.id.quantity);
                    TextView rcost = (TextView) child.findViewById(R.id.cost);
                    TextView rtotal = (TextView) child.findViewById(R.id.total);
                    ritemname.setText(gameObj.getString("Item"));
                    rquantity.setText(gameObj.getString("Price"));
                    rcost.setText(gameObj.getString("Quantity"));
                    rtotal.setText(gameObj.getString("Amount"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText((mContext),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                Log.d("abc", "onResponse: " + gameObj);
                Log.d("abcd", "onResponse: " + itemsarray);

            }
//           adapter2 = new GetMyreceptItemAdapter(itemsarray);
//            holder.listitem.setAdapter(adapter2);
        }

        holder.linearHeaderLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				callDetailActivity(position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (receptsModelArrayList != null && receptsModelArrayList.size() > 0) ? receptsModelArrayList.size() : 0;

    }

//    @SuppressLint({"ViewTag", "InflateParams"})
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//        ViewHolder holder = null;
//        if (v == null) {
//            v = li.inflate(R.layout.recepts, null);
//            holder = new ViewHolder();
//            holder.orderid = (TextView) v.findViewById(R.id.orderid);
//            holder.date = (TextView) v.findViewById(R.id.orderdate);
//            holder.linearHeaderLayout = (LinearLayout) v.findViewById(R.id.linearHeaderLayout);
//            holder.amount = (TextView) v.findViewById(R.id.amount);
//            holder.shipingamount = (TextView) v.findViewById(R.id.shippingcgarges);
//            holder.total = (TextView) v.findViewById(R.id.totalamount);
//            holder.address = (TextView) v.findViewById(R.id.address);
//            LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);
//
//            v.setTag(holder);
//        } else {
//            holder = (ViewHolder) v.getTag();
//        }
//        holder.orderid.setText("Invoice #" + receptsModelArrayList.get(position).corderId);
//        holder.date.setText("Order Date : " + receptsModelArrayList.get(position).orderDate);
//        holder.amount.setText("Amount : " + receptsModelArrayList.get(position).amount);
//        holder.shipingamount.setText("ShippingCost : " + receptsModelArrayList.get(position).shippingCost);
//        holder.total.setText("TotalAmount : " + receptsModelArrayList.get(position).totalAmount);
//        holder.address.setText("Address : " + receptsModelArrayList.get(position).address);
//
//        receptsModelArrayList1 = receptsModelArrayList.get(position).items;
//        Log.d("receptsModelArrayList1", "getView: " + receptsModelArrayList1);
//        if (!receptsModelArrayList1.equals("")) {
//
//            LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);
//            layout.removeAllViews();
//            for (int i = 0; i < receptsModelArrayList1.length(); i++) {
//                JSONObject gameObj = null;
//                try {
////                    v.setTag(holder);
//                    gameObj = (JSONObject) receptsModelArrayList1.get(i);
//                    Log.d("1", "getView: " + gameObj.getString("Item"));
//                    Log.d("2", "getView: " + gameObj.getString("Price"));
//                    Log.d("3", "getView: " + gameObj.getString("Quantity"));
//                    Log.d("4", "getView: " + gameObj.getString("Amount"));
//                    View child = li.inflate(R.layout.receptitemrow, null);
//                    layout.addView(child);
//                    TextView ritemname = (TextView) child.findViewById(R.id.itemname);
//                    TextView rquantity = (TextView) child.findViewById(R.id.quantity);
//                    TextView rcost = (TextView) child.findViewById(R.id.cost);
//                    TextView rtotal = (TextView) child.findViewById(R.id.total);
//                    ritemname.setText(gameObj.getString("Item"));
//                    rquantity.setText(gameObj.getString("Price"));
//                    rcost.setText(gameObj.getString("Quantity"));
//                    rtotal.setText(gameObj.getString("Amount"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText((mContext),
//                            "Error: " + e.getMessage(),
//                            Toast.LENGTH_LONG).show();
//                }
//                Log.d("abc", "onResponse: " + gameObj);
//                Log.d("abcd", "onResponse: " + itemsarray);
//
//            }
////           adapter2 = new GetMyreceptItemAdapter(itemsarray);
////            holder.listitem.setAdapter(adapter2);
//        }
//
//        holder.linearHeaderLayout.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////				callDetailActivity(position);
//            }
//        });
//        return v;
//    }

    public class GetMyreceptItemAdapter extends BaseAdapter {
        private List<Items> receptsModelArrayList = new ArrayList<Items>();

        public GetMyreceptItemAdapter(ArrayList<Items> itemsarray) {
            receptsModelArrayList = itemsarray;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {


            View v = convertView;
            ViewHolder holde = null;
            if (v == null) {
                v = li.inflate(R.layout.receptitemrow, null);
                holde = new ViewHolder();
                holde.ritemname = (TextView) v.findViewById(R.id.itemname);
                holde.rquantity = (TextView) v.findViewById(R.id.quantity);
                holde.rcost = (TextView) v.findViewById(R.id.cost);
                holde.rtotal = (TextView) v.findViewById(R.id.total);
                v.setTag(holde);
            } else {
                holde = (ViewHolder) v.getTag();
            }
            holde.ritemname.setText(receptsModelArrayList.get(position).item);
            holde.rquantity.setText(receptsModelArrayList.get(position).quantity);
            holde.rcost.setText(receptsModelArrayList.get(position).price);
            holde.rtotal.setText(receptsModelArrayList.get(position).amount);

            return v;
        }
    }

    class ViewHolder {
        TextView date, orderid, total, shipingamount, amount, address, ritemname, rcost, rquantity, rtotal;
        LinearLayout linearHeaderLayout;
        ListView listitem;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView date, orderid, total, shipingamount, amount, address, ritemname, rcost, rquantity, rtotal;
        LinearLayout linearHeaderLayout;
        ListView listitem;

        public ProductViewHolder(View v) {
            super(v);

            orderid = (TextView) v.findViewById(R.id.orderid);
            date = (TextView) v.findViewById(R.id.orderdate);
            linearHeaderLayout = (LinearLayout) v.findViewById(R.id.linearHeaderLayout);
            amount = (TextView) v.findViewById(R.id.amount);
            shipingamount = (TextView) v.findViewById(R.id.shippingcgarges);
            total = (TextView) v.findViewById(R.id.totalamount);
            address = (TextView) v.findViewById(R.id.address);
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.layout);

        }
    }
}
