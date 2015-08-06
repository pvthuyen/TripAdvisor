package com.example.pvthuyen.tripadvisor;

import android.content.Context;
import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by pvthuyen on 8/2/15.
 */
public class Place {

    protected final static String TAG_NAME = "name";
    protected final static String TAG_DESCRIPTION = "description";
    protected final static String TAG_photoName = "photo_name";
    protected final static String TAG_RATING = "rating";
    protected final static String TAG_ADDRESS = "address";
    protected final static String TAG_PHONE = "phone";
    protected final static String TAG_WEBSITE = "website";
    protected final static String TAG_OPENTIME = "open_time";
    protected final static String TAG_CLOSETIME = "close_time";
    protected final static String TAG_REGION = "region";
    protected final static String TAG_LONG = "long";
    protected final static String TAG_LAT = "lat";

    Context context;
    protected String name;
    protected String description;
    protected int photo;
    protected String photoName;
    protected int rating;
    protected String address;
    protected String phone;
    protected String website;
    protected String open_time;
    protected String close_time;
    protected String region;
    protected double lng;
    protected double lat;

    public Place(String name, String description, String address, String phone, String website, String open_time, String close_time, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.open_time = open_time;
        this.close_time = close_time;
        this.lat = lat;
        this.lng = lng;
    }

    public Place(Context context, JSONObject jsonObject) {
        try {
            this.name = jsonObject.getString(Place.TAG_NAME);
            this.description = jsonObject.getString(Place.TAG_DESCRIPTION);
            photoName = jsonObject.getString(TAG_photoName);
            this.photo = context.getResources().getIdentifier(photoName, "drawable", context.getPackageName());;
            this.rating = jsonObject.getInt(Place.TAG_RATING);
            this.address = jsonObject.getString(Place.TAG_ADDRESS);
            this.phone = jsonObject.getString(Place.TAG_PHONE);
            this.website = jsonObject.getString(Place.TAG_WEBSITE);
            this.open_time = jsonObject.getString(Place.TAG_OPENTIME);
            this.close_time = jsonObject.getString(Place.TAG_CLOSETIME);
            this.region = jsonObject.getString(Place.TAG_REGION);
            this.lng = jsonObject.getDouble(Place.TAG_LONG);
            this.lat = jsonObject.getDouble(Place.TAG_LAT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPhoto() {
        return photo;
    }

    public int getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getOpen_time() {
        return open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public String getRegion() {
        return region;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    /*
     protected final static String TAG_NAME = "name";
    protected final static String TAG_DESCRIPTION = "description";
    protected final static String TAG_photoName = "photo_name";
    protected final static String TAG_RATING = "rating";
    protected final static String TAG_ADDRESS = "address";
    protected final static String TAG_PHONE = "phone";
    protected final static String TAG_WEBSITE = "website";
    protected final static String TAG_OPENTIME = "open_time";
    protected final static String TAG_CLOSETIME = "close_time";
    protected final static String TAG_REGION = "region";
    protected final static String TAG_LONG = "long";
    protected final static String TAG_LAT = "lat";
     */

    public void writeToJSON(JsonWriter jsonWriter) {
        try {
            jsonWriter.beginObject();
            jsonWriter.name(TAG_NAME).value(name);
            jsonWriter.name(TAG_DESCRIPTION).value(description);
            jsonWriter.name(TAG_photoName).value(photoName);
            jsonWriter.name(TAG_RATING).value(rating);
            jsonWriter.name(TAG_ADDRESS).value(address);
            jsonWriter.name(TAG_PHONE).value(phone);
            jsonWriter.name(TAG_WEBSITE).value(website);
            jsonWriter.name(TAG_OPENTIME).value(open_time);
            jsonWriter.name(TAG_CLOSETIME).value(close_time);
            jsonWriter.name(TAG_REGION).value(region);
            jsonWriter.name(TAG_LONG).value(lng);
            jsonWriter.name(TAG_LAT).value(lat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
