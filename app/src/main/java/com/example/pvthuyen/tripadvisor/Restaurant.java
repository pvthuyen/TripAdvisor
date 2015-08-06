package com.example.pvthuyen.tripadvisor;

import android.content.Context;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pvthuyen on 8/2/15.
 */
public class Restaurant extends Place {
    private final static String TAG_MENU = "menu";
    private final static String TAG_DISH = "dish";
    private final static String TAG_DISHNAME = "dish_name";
    private final static String TAG_DES = "des";
    private final static String TAG_PHOTO = "photo";
    private final static String TAG_PRICE = "price";
    public class Dish {
        private String dishName;
        private String description;
        private int photo;
        private String photoName;
        private int price;

        public Dish(Context context, JSONObject jsonObject) {
            try {
                this.dishName = jsonObject.getString(Restaurant.TAG_DISHNAME);
                this.description = jsonObject.getString(Restaurant.TAG_DES);
                this.photoName = jsonObject.getString(Restaurant.TAG_PHOTO);
                this.photo = context.getResources().getIdentifier(this.photoName, "drawable", context.getPackageName());
                this.price = jsonObject.getInt(Restaurant.TAG_PRICE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String getDishName() {
            return dishName;
        }

        public String getDescription() {
            return description;
        }

        public int getPhoto() {
            return photo;
        }

        public int getPrice() {
            return price;
        }
    }

    ArrayList<Dish> menu;

    public Restaurant(String name, String description, String address, String phone, String website, String open_time, String close_time, double lat, double lng) {
        super(name, description, address, phone, website, open_time, close_time, lat, lng);
        menu = new ArrayList<>();
    }

    public Restaurant(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
        this.menu = new ArrayList<Dish>();
        try {
            JSONArray jsonArray = jsonObject.getJSONObject(Restaurant.TAG_MENU).getJSONArray(Restaurant.TAG_DISH);

            for (int i = 0; i < jsonArray.length(); ++i) {
                this.menu.add(new Dish(context, jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    /*
    private final static String TAG_MENU = "menu";
    private final static String TAG_DISH = "dish";
    private final static String TAG_DISHNAME = "dish_name";
    private final static String TAG_DES = "des";
    private final static String TAG_PHOTO = "photo";
    private final static String TAG_PRICE = "price";
     */

    @Override
    public void writeToJSON(JsonWriter jsonWriter) {
        super.writeToJSON(jsonWriter);
        try {
            jsonWriter.name(TAG_MENU).beginObject();
            jsonWriter.name(TAG_DISH).beginArray();
            for (int i = 0; i < menu.size(); ++i) {
                jsonWriter.beginObject();
                jsonWriter.name(TAG_DISHNAME).value(menu.get(i).dishName);
                jsonWriter.name(TAG_DES).value(menu.get(i).description);
                jsonWriter.name(TAG_PHOTO).value(menu.get(i).photoName);
                jsonWriter.name(TAG_PRICE).value(menu.get(i).price);
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
