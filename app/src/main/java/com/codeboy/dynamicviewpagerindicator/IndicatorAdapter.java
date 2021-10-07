package com.codeboy.dynamicviewpagerindicator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder> {

    private Context RestaurantContx;
    private ArrayList<RestaurantInfo> restgaurants;
    private callback listener;
    private View selectView;
    private int selectedIndex;

    public IndicatorAdapter(Context restaurantContx, ArrayList<RestaurantInfo> restgaurants, callback listener) {
        RestaurantContx = restaurantContx;
        this.restgaurants = restgaurants;
        this.listener = listener;
        selectedIndex = 0;
    }

    @NonNull
    @Override
    public IndicatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(RestaurantContx);
        View itemView = inflater.inflate(R.layout.layout_indicator_item, null, false);
        return new IndicatorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final IndicatorViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        RestaurantInfo restaurant = restgaurants.get(position);
        Resources resources = RestaurantContx.getResources();
        final int resourceId = resources.getIdentifier(restaurant.getLogoName(), "drawable", RestaurantContx.getPackageName());
        Glide.with(RestaurantContx)
                .load(resourceId)
                .apply(new RequestOptions().placeholder(R.drawable.food_background_1).fitCenter())
                .into(holder.logo);

        if (selectedIndex == position) {
            holder.bottomIndicator.setBackgroundColor(RestaurantContx.getResources().getColor(R.color.white));
            holder.bottomIndicator.setVisibility(View.VISIBLE);
            selectedIndex = position;
            selectView = holder.bottomIndicator;
        } else {
            holder.bottomIndicator.setBackgroundColor(Color.TRANSPARENT);
            holder.bottomIndicator.setVisibility(View.GONE);
        }


        holder.clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.bottomIndicator.setBackgroundColor(RestaurantContx.getResources().getColor(R.color.white));
                holder.bottomIndicator.setVisibility(View.VISIBLE);
                if (selectedIndex != position) {
                    selectView.setBackgroundColor(Color.TRANSPARENT);
                    selectView.setVisibility(View.GONE);
                }
                selectView = holder.bottomIndicator;
                selectedIndex = position;
                listener.onTitleClicked(position);
            }
        });
    }

    public void setSelectedIndex(int position) {
        selectedIndex = position;
    }

    public interface callback {
        void onTitleClicked(int position);
    }

    @Override
    public int getItemCount() {
        return restgaurants.size();
    }

    class IndicatorViewHolder extends RecyclerView.ViewHolder {
        //define sub views
        ImageView logo;
        RatingBar ratings;
        View clicker;
        View bottomIndicator;

        IndicatorViewHolder(@NonNull View itemView) {
            super(itemView);
            //instantiate views
            logo = itemView.findViewById(R.id.logo);
            ratings = itemView.findViewById(R.id.rating);
            clicker = itemView.findViewById(R.id.clickr);
            bottomIndicator = itemView.findViewById(R.id.bottom_indicator);
        }
    }

}
