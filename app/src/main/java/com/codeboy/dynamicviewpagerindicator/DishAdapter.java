package com.codeboy.dynamicviewpagerindicator;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DistViewHolder> {

    private Context dishContx;
    private ArrayList<Dish> dishes;
    private Callback listener;

    DishAdapter(Context dishContx, ArrayList<Dish> dishes, Callback listener) {
        this.dishContx = dishContx;
        this.dishes = dishes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(dishContx);
        View itemView = inflater.inflate(R.layout.layout_dish_item, null, false);
        return new DistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DistViewHolder holder, int position) {
        Dish dish = dishes.get(position);

        holder.dish_name.setText(dish.getDishName());
        holder.dish_description.setText(dish.getDishDescription());

        Resources resources = dishContx.getResources();
        final int resourceId = resources.getIdentifier(dish.getDishIcon(), "drawable", dishContx.getPackageName());
        Glide.with(dishContx)
                .load(resourceId)
                .apply(new RequestOptions().placeholder(R.drawable.food_background_1).fitCenter())
                .into(holder.dishPic);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDishClicked();
            }
        });
    }

    public interface Callback {
        void onDishClicked();
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    class DistViewHolder extends RecyclerView.ViewHolder {
        //instanciate views
        ImageView dishPic;
        TextView dish_name;
        TextView dish_description;
        ImageButton order;

        DistViewHolder(@NonNull View itemView) {
            super(itemView);
            //init views
            dishPic = itemView.findViewById(R.id.dish_image);
            dish_name = itemView.findViewById(R.id.dish_name);
            dish_description = itemView.findViewById(R.id.dish_description);
            order = itemView.findViewById(R.id.add_menu);
        }
    }

}
