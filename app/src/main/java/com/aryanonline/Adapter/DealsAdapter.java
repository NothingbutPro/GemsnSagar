package com.aryanonline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.DealsModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder>  {
    private static final String TAG = "DealsAdapter";
    private ArrayList<DealsModel> dealList;
    public Context context;
    String resId = "";
    String finalStatus = "";
    String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idProductName1;
        LinearLayout card;
        ImageView Image1;
        int pos;

        public ViewHolder(View view) {
            super(view);

            idProductName1 = (TextView) view.findViewById(com.aryanonline.R.id.idProductName1);
            Image1 = (ImageView) view.findViewById(com.aryanonline.R.id.Image1);
         //   card = (LinearLayout) view.findViewById(R.id.card_view);
        }
    }

    public static Context mContext;

    public DealsAdapter(Context mContext, ArrayList<DealsModel> deals_list) {
        context = mContext;
        dealList = deals_list;

    }

    @Override
    public DealsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.aryanonline.R.layout.demoview, parent, false);

        return new DealsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DealsAdapter.ViewHolder viewHolder, final int position) {
        DealsModel dealsModel = dealList.get(position);
   //     viewHolder.idProductName1.setText(dealsModel.getDeals_description());
        Image = dealsModel.getProductImage();
        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL+Image)
                .placeholder(com.aryanonline.R.drawable.aplogo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(viewHolder.Image1);
       // viewHolder.card.setTag(viewHolder);
        viewHolder.pos = position;

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

}
