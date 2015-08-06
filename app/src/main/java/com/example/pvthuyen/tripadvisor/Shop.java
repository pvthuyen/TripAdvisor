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
public class Shop extends Place {
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PNAME = "pname";
    private static final String TAG_DESC = "desc";
    private static final String TAG_PHOTO = "photo";

    public class Product {
        private String productName;
        private String description;
        private int photo;
        private String photoName;
        Product(Context context, JSONObject jsonObject) {
            try {
                this.productName = jsonObject.getString(Shop.TAG_PNAME);
                this.description = jsonObject.getString(Shop.TAG_DESC);
                this.photoName = jsonObject.getString(Shop.TAG_PHOTO);
                this.photo = context.getResources().getIdentifier(this.photoName, "drawable", context.getPackageName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String getProductName() {
            return productName;
        }

        public String getDescription() {
            return description;
        }

        public int getPhoto() {
            return photo;
        }
    }

    private ArrayList<Product> products;

    public Shop(String name, String description, String address, String phone, String website, String open_time, String close_time, double lat, double lng) {
        super(name, description, address, phone, website, open_time, close_time, lat, lng);
        products = new ArrayList<>();
    }

    public Shop(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
        products = new ArrayList<Product>();
        try {
            JSONArray jsonArray = jsonObject.getJSONObject(Shop.TAG_PRODUCTS).getJSONArray(Shop.TAG_PRODUCT);

            for (int i = 0; i < jsonArray.length(); ++i) {
                products.add(new Product(context, jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    /*
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PNAME = "pname";
    private static final String TAG_DESC = "desc";
    private static final String TAG_PHOTO = "photo";
     */
    @Override
    public void writeToJSON(JsonWriter jsonWriter) {
        super.writeToJSON(jsonWriter);
        try {
            jsonWriter.name(TAG_PRODUCTS).beginObject();
            jsonWriter.name(TAG_PRODUCT).beginArray();
            for (int i = 0; i < products.size(); ++i) {
                jsonWriter.beginObject();
                jsonWriter.name(TAG_PNAME).value(products.get(i).productName);
                jsonWriter.name(TAG_DESC).value(products.get(i).description);
                jsonWriter.name(TAG_PHOTO).value(products.get(i).photoName);
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
