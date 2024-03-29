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
import com.aryanonline.Model.OfferModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder>  {
    private static final String TAG = "OfferAdapter";
    private ArrayList<OfferModel> OffList;
    public Context context;
    String resId = "";
    String finalStatus = "";
     String Image;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idProductName;
        LinearLayout card;
        ImageView idProductImage;
        int pos;

        public ViewHolder(View view) {
            super(view);

           // idProductName = (TextView) view.findViewById(R.id.idProductName);
            idProductImage = (ImageView) view.findViewById(com.aryanonline.R.id.idProductImage);
            card = (LinearLayout) view.findViewById(com.aryanonline.R.id.card_view);
        }
    }

    public static Context mContext;

    public OfferAdapter(Context mContext, ArrayList<OfferModel> offer_list) {
        context = mContext;
        OffList = offer_list;

    }

    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(com.aryanonline.R.layout.vertical_menu, parent, false);

        return new OfferAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final OfferAdapter.ViewHolder viewHolder, final int position) {
        OfferModel offerModel = OffList.get(position);
     //   viewHolder.idProductName.setText(offerModel.getOffersCatDesc());
        Image = offerModel.getImage();
        Glide.with(context)
                .load(BaseURL.IMG_PRODUCT_URL+Image)
                .placeholder(com.aryanonline.R.drawable.aplogo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(viewHolder.idProductImage);
        viewHolder.card.setTag(viewHolder);
        viewHolder.pos = position;

    }

    @Override
    public int getItemCount() {
        return OffList.size();
    }

}
