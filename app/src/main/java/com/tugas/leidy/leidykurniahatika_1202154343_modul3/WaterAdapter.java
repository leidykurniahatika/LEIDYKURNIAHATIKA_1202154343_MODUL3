package com.tugas.leidy.leidykurniahatika_1202154343_modul3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.WaterViewHolder> {

    //Member variables
    private GradientDrawable mGradientDrawable; //inisialisasi drawable
    private ArrayList<Water> mWaterData; //inisialisasi array
    private Context mContext; //inisialisasi context

    WaterAdapter(Context context, ArrayList<Water> waterData) {
        this.mWaterData = waterData; //mengambil nilai water data
        this.mContext = context; //mengambil nilai context

        //Prepare gray placeholder
        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        //Make the placeholder same size as the images
        Drawable drawable = ContextCompat.getDrawable
                (mContext, R.drawable.aqua);
        if (drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    @Override
    public WaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WaterViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_water, parent, false), mGradientDrawable); //mengoper data dari adapter
    }

    @Override
    public void onBindViewHolder(WaterViewHolder holder, int position) {

        //Get the current water
        Water currentWater = mWaterData.get(position);

        //Bind the data to the views
        holder.bindTo(currentWater);
        Glide.with(mContext).load(currentWater.getImageResource()).into(holder.mWaterImage);

    }

    @Override
    public int getItemCount() {
        return mWaterData.size();
    } //get item count dari water data array


    static class WaterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        //inisialisasi seluruh variabel
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mWaterImage;
        private Context mContext;
        private Water mCurrentWater;
        private GradientDrawable mGradientDrawable;

        WaterViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView) itemView.findViewById(R.id.newsTitle);
            mInfoText = (TextView) itemView.findViewById(R.id.subTitle);
            mWaterImage = (ImageView) itemView.findViewById(R.id.waterImage);


            mContext = context;
            mGradientDrawable = gradientDrawable;

            //Set the OnClickListener to the whole view
            itemView.setOnClickListener(this);
        }

        void bindTo(Water currentWater) {
            //Populate the textviews with data
            mTitleText.setText(currentWater.getTitle());
            mInfoText.setText(currentWater.getInfo());

            //Get the current water
            mCurrentWater = currentWater;


            //Load the images into the ImageView using the Glide library
            Glide.with(mContext).load(currentWater.
                    getImageResource()).placeholder(mGradientDrawable).into(mWaterImage);
        }

        @Override
        public void onClick(View view) {

            //Set up the detail intent
            Intent detailIntent = Water.starter(mContext, mCurrentWater.getTitle(),
                    mCurrentWater.getImageResource());

            //Start the detail activity
            mContext.startActivity(detailIntent);
        }
    }
}
