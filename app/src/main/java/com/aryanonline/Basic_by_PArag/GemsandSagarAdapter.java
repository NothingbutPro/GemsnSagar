package com.aryanonline.Basic_by_PArag;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aryanonline.Adapter.Product_adapter;
import com.aryanonline.Config.BaseURL;
import com.aryanonline.MainActivity;
import com.aryanonline.Model.Product_model;
import com.aryanonline.R;
import com.aryanonline.ShowProdDetail;
import com.aryanonline.util.DatabaseHandler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GemsandSagarAdapter  extends RecyclerView.Adapter<GemsandSagarAdapter.MyViewHolder>
        implements Filterable {

    private List<Product_model> modelList;
    Product_model product_model;
    private List<Product_model> mFilteredList;
    private Context context;
    private DatabaseHandler dbcart;



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_title, tv_price, tv_total, tv_contetiy, tv_add, mrpPrice;
        public ImageView iv_logo, iv_plus, iv_minus, iv_remove;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(com.aryanonline.R.id.tv_subcat_title);
            tv_price = (TextView) view.findViewById(com.aryanonline.R.id.tv_subcat_price);
            tv_total = (TextView) view.findViewById(com.aryanonline.R.id.tv_subcat_total);
            tv_contetiy = (TextView) view.findViewById(com.aryanonline.R.id.tv_subcat_contetiy);
            tv_add = (TextView) view.findViewById(com.aryanonline.R.id.tv_subcat_add);
            iv_logo = (ImageView) view.findViewById(com.aryanonline.R.id.iv_subcat_img);
            iv_plus = (ImageView) view.findViewById(com.aryanonline.R.id.iv_subcat_plus);
            iv_minus = (ImageView) view.findViewById(com.aryanonline.R.id.iv_subcat_minus);
            iv_remove = (ImageView) view.findViewById(com.aryanonline.R.id.iv_subcat_remove);
            mrpPrice = (TextView) view.findViewById(com.aryanonline.R.id.mrpPrice);

            iv_remove.setVisibility(View.GONE);

            iv_minus.setOnClickListener(this);
            iv_plus.setOnClickListener(this);
            tv_add.setOnClickListener(this);
            iv_logo.setOnClickListener(this);

            CardView cardView = (CardView) view.findViewById(com.aryanonline.R.id.card_view);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int position = getAdapterPosition();

            if (id == com.aryanonline.R.id.iv_subcat_plus) {

                int qty = Integer.valueOf(tv_contetiy.getText().toString());
                qty = qty + 1;

                tv_contetiy.setText(String.valueOf(qty));

            } else if (id == com.aryanonline.R.id.iv_subcat_minus) {

                int qty = 0;
                if (!tv_contetiy.getText().toString().equalsIgnoreCase(""))
                    qty = Integer.valueOf(tv_contetiy.getText().toString());

                if (qty > 0) {
                    qty = qty - 1;
                    tv_contetiy.setText(String.valueOf(qty));
                }

            } else if (id == com.aryanonline.R.id.tv_subcat_add) {

                HashMap<String, String> map = new HashMap<>();

                map.put("product_id", modelList.get(position).getProduct_id());
                map.put("category_id", modelList.get(position).getCategory_id());
                map.put("product_image", modelList.get(position).getProduct_image());
                map.put("increament", modelList.get(position).getIncreament());
                map.put("product_name", modelList.get(position).getProduct_name());
                map.put("price", modelList.get(position).getPrice());
                map.put("stock", modelList.get(position).getIn_stock());
                map.put("title", modelList.get(position).getTitle());
                map.put("unit", modelList.get(position).getUnit());
                map.put("Mrp", modelList.get(position).getMrp());

                map.put("unit_value", modelList.get(position).getUnit_value());

                if (!tv_contetiy.getText().toString().equalsIgnoreCase("0")) {

                    if (dbcart.isInCart(map.get("product_id"))) {
                        //    dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
                        tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
                    } else {
                        //  dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
                        tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
                    }
                } else {
                    dbcart.removeItemFromCart(map.get("product_id"));
                    tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_add));
                }

                Double items = Double.parseDouble(dbcart.getInCartItemQty(map.get("product_id")));
                Double price = Double.parseDouble(map.get("price"));

                tv_total.setText("" + price * items);
                //      mrpPrice.setText(map.get("Mrp"));
                ((MainActivity) context).setCartCounter("" + dbcart.getCartCount());

            } else if (id == com.aryanonline.R.id.iv_subcat_img) {
                //showImage(modelList.get(position).getProduct_image());

                Intent  intent = new Intent(context, ShowProdDetail.class);
                intent.putExtra("Product_Model",product_model);

                intent.putExtra("product_id", modelList.get(position).getProduct_id());
                intent.putExtra("category_id", modelList.get(position).getCategory_id());
                intent.putExtra("product_image", modelList.get(position).getProduct_image());
                intent.putExtra("increament", modelList.get(position).getIncreament());
                intent.putExtra("product_name", modelList.get(position).getProduct_name());
                intent.putExtra("price", modelList.get(position).getPrice());
                intent.putExtra("stock", modelList.get(position).getIn_stock());
                intent.putExtra("title", modelList.get(position).getTitle());
                intent.putExtra("unit", modelList.get(position).getUnit());
                intent.putExtra("Mrp", modelList.get(position).getMrp());
                intent.putExtra("unit_value", modelList.get(position).getUnit_value());

                context.startActivity(intent);
                // Toast.makeText(context, "pp"+prodId, Toast.LENGTH_SHORT).show();
            } else if (id == com.aryanonline.R.id.card_view) {

                Intent  intent = new Intent(context, ShowProdDetail.class);
                intent.putExtra("Product_Model",product_model);

                intent.putExtra("product_id", modelList.get(position).getProduct_id());
                intent.putExtra("category_id", modelList.get(position).getCategory_id());
                intent.putExtra("product_image", modelList.get(position).getProduct_image());
                intent.putExtra("increament", modelList.get(position).getIncreament());
                intent.putExtra("product_name", modelList.get(position).getProduct_name());
                intent.putExtra("price", modelList.get(position).getPrice());
                intent.putExtra("stock", modelList.get(position).getIn_stock());
                intent.putExtra("title", modelList.get(position).getTitle());
                intent.putExtra("unit", modelList.get(position).getUnit());
                intent.putExtra("Mrp", modelList.get(position).getMrp());
                intent.putExtra("unit_value", modelList.get(position).getUnit_value());

                context.startActivity(intent);
             /*   showProductDetail(modelList.get(position).getProduct_image(),
                        modelList.get(position).getTitle(),
                        modelList.get(position).getProduct_description(),
                        modelList.get(position).getProduct_name(),
                        position, tv_contetiy.getText().toString());*/
            }
        }
    }

    public GemsandSagarAdapter(List<Product_model> modelList, Context context) {
        this.modelList = modelList;
        this.mFilteredList = modelList;

        dbcart = new DatabaseHandler(context);
    }

    @Override
    public GemsandSagarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.aryanonline.R.layout.row_product_rv, parent, false);

        context = parent.getContext();

        return new GemsandSagarAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GemsandSagarAdapter.MyViewHolder holder, int position) {
        Product_model mList = modelList.get(position);

        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + mList.getProduct_image())
                .centerCrop()
                .placeholder(com.aryanonline.R.drawable.aplogo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.iv_logo);

        holder.tv_title.setText(mList.getProduct_name());
        holder.mrpPrice.setText(mList.getMrp());
        holder.tv_price.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_price) + mList.getUnit_value() + " " +
                mList.getUnit() + " " + context.getResources().getString(com.aryanonline.R.string.currency) + " " + mList.getPrice());

        if (dbcart.isInCart(mList.getProduct_id())) {
            holder.tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
            holder.tv_contetiy.setText(dbcart.getCartItemQty(mList.getProduct_id()));
        } else {
            holder.tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_add));
        }

        Double items = Double.parseDouble(dbcart.getInCartItemQty(mList.getProduct_id()));
        Double price = Double.parseDouble(mList.getPrice());

        holder.tv_total.setText("" + price * items);

    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = modelList;
                } else {

                    ArrayList<Product_model> filteredList = new ArrayList<>();

                    for (Product_model androidVersion : modelList) {

                        if (androidVersion.getProduct_name().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Product_model>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    private void showImage(String image) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.aryanonline.R.layout.product_image_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        ImageView iv_image_cancle = (ImageView) dialog.findViewById(com.aryanonline.R.id.iv_dialog_cancle);
        ImageView iv_image = (ImageView) dialog.findViewById(com.aryanonline.R.id.iv_dialog_img);

        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + image)
                .centerCrop()
                .placeholder(com.aryanonline.R.drawable.aplogo)
                .crossFade()
                .into(iv_image);

        iv_image_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void showProductDetail(String image, String title, String description, String detail, final int position, String qty) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.aryanonline.R.layout.dialog_product_detail);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        ImageView iv_image = (ImageView) dialog.findViewById(com.aryanonline.R.id.iv_product_detail_img);
        ImageView iv_minus = (ImageView) dialog.findViewById(com.aryanonline.R.id.iv_subcat_minus);
        ImageView iv_plus = (ImageView) dialog.findViewById(com.aryanonline.R.id.iv_subcat_plus);
        TextView tv_title = (TextView) dialog.findViewById(com.aryanonline.R.id.tv_product_detail_title);
        TextView tv_detail = (TextView) dialog.findViewById(com.aryanonline.R.id.tv_product_detail);
        final TextView tv_contetiy = (TextView) dialog.findViewById(com.aryanonline.R.id.tv_subcat_contetiy);
        final TextView tv_add = (TextView) dialog.findViewById(com.aryanonline.R.id.tv_subcat_add);

        tv_title.setText(title);
        tv_detail.setText(detail);
        tv_contetiy.setText(qty);
        tv_detail.setText(description);

        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL + image)
                .centerCrop()
                .placeholder(com.aryanonline.R.drawable.aplogo)
                .crossFade()
                .into(iv_image);

        if (dbcart.isInCart(modelList.get(position).getProduct_id())) {
            tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
            tv_contetiy.setText(dbcart.getCartItemQty(modelList.get(position).getProduct_id()));
        } else {
            tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_add));
        }

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("product_id", modelList.get(position).getProduct_id());
                map.put("category_id", modelList.get(position).getCategory_id());
                map.put("product_image", modelList.get(position).getProduct_image());
                map.put("increament", modelList.get(position).getIncreament());
                map.put("product_name", modelList.get(position).getProduct_name());

                map.put("price", modelList.get(position).getPrice());
                map.put("stock", modelList.get(position).getIn_stock());
                map.put("title", modelList.get(position).getTitle());
                map.put("unit", modelList.get(position).getUnit());

                map.put("unit_value", modelList.get(position).getUnit_value());

                if (!tv_contetiy.getText().toString().equalsIgnoreCase("0")) {

                    if (dbcart.isInCart(map.get("product_id"))) {
                        //  dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
                        tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
                    } else {
                        //  dbcart.setCart(map, Float.valueOf(tv_contetiy.getText().toString()));
                        tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_update));
                    }
                } else {
                    dbcart.removeItemFromCart(map.get("product_id"));
                    tv_add.setText(context.getResources().getString(com.aryanonline.R.string.tv_pro_add));
                }

                Double items = Double.parseDouble(dbcart.getInCartItemQty(map.get("product_id")));
                Double price = Double.parseDouble(map.get("price"));

                ((MainActivity) context).setCartCounter("" + dbcart.getCartCount());

                notifyItemChanged(position);

            }
        });

        iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.valueOf(tv_contetiy.getText().toString());
                qty = qty + 1;

                tv_contetiy.setText(String.valueOf(qty));
            }
        });

        iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = 0;
                if (!tv_contetiy.getText().toString().equalsIgnoreCase(""))
                    qty = Integer.valueOf(tv_contetiy.getText().toString());

                if (qty > 0) {
                    qty = qty - 1;
                    tv_contetiy.setText(String.valueOf(qty));
                }
            }
        });

    }

}