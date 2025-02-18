package com.codeboy.dynamicviewpagerindicator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Restaurant extends Fragment {

    private RestaurantInfo resto;

    public void setRestaurant(RestaurantInfo restaurant) {
        this.resto = restaurant;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_restaurant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView restuarantName = view.findViewById(R.id.resto_name);
        restuarantName.setText(resto.getRestaurantName());
        TextView restaurantDescription = view.findViewById(R.id.resto_description);
        restaurantDescription.setText(resto.getDescription());


        resto.setDishes(getAllDishes());
        RecyclerView dishesList = view.findViewById(R.id.dish_recycler);
        dishesList.hasFixedSize();
        dishesList.setLayoutManager(new LinearLayoutManager(getActivity()));

        DishAdapter.Callback callback = new DishAdapter.Callback() {
            @Override
            public void onDishClicked() {
                //add selected dish to user menu
            }
        };

        DishAdapter allDishes = new DishAdapter(getActivity(), resto.getDishes(), callback);
        dishesList.setAdapter(allDishes);
    }


    private ArrayList<Dish> getAllDishes() {

        //get dishes first
        ArrayList<Dish> dishes = new ArrayList<>();
        String[] dishNames = this.getResources().getStringArray(R.array.dishes);
        String[] dishPics = this.getResources().getStringArray(R.array.dish_icons);
        String[] dishDescription = this.getResources().getStringArray(R.array.dish_descriptions);

        for (int i = 0; i < dishNames.length; i++) {
            String dish_name = dishNames[i];
            String dish_description = dishDescription[i];
            String dish_icon = dishPics[i];
            Dish dish = new Dish(dish_name, dish_description, dish_icon);
            dishes.add(dish);
        }
        return dishes;
    }


}
