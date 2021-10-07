package com.codeboy.dynamicviewpagerindicator;

import java.util.ArrayList;

public class RestaurantInfo {

    private String restaurantName;
    private String description;
    private String logoName;
    private ArrayList<Dish> dishes;

    public RestaurantInfo() {

    }

    RestaurantInfo(String restaurantName, String description, String logoName) {
        this.restaurantName = restaurantName;
        this.description = description;
        this.logoName = logoName;
    }

    String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    ArrayList<Dish> getDishes() {
        return dishes;
    }

    void setDishes(ArrayList<Dish> dishes) {
        this.dishes = dishes;
    }
}
