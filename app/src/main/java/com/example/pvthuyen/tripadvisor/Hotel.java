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
public class Hotel extends Place {
    private static final String TAG_ROOMTYPES = "room_types";
    private static final String TAG_ROOMTYPE = "room_type";
    private static final String TAG_ROOMNAME = "rname";
    private static final String TAG_INFO = "info";
    private static final String TAG_DESC = "desc";
    private static final String TAG_PHOTO = "photo";
    private final static String TAG_PRICE = "price";

    public class RoomType {
        private String name;
        private String info;
        private String desc;
        private int photo;
        private String photoName;
        private double price;

        public RoomType() {
            name = "";
            info = "";
            desc = "";
            price = 0;
        }
        public RoomType(Context context, JSONObject jsonObject) {
            try {
                this.name = jsonObject.getString(Hotel.TAG_ROOMNAME);
                this.info = jsonObject.getString(Hotel.TAG_INFO);
                this.desc = jsonObject.getString(Hotel.TAG_DESC);
                this.photoName = jsonObject.getString(Hotel.TAG_PHOTO);
                this.photo = context.getResources().getIdentifier(photoName, "drawable", context.getPackageName());
                this.price = jsonObject.getDouble(Hotel.TAG_PRICE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.photo = context.getResources().getIdentifier(photoName, "drawable", context.getPackageName());
        }

        public String getName() {
            return name;
        }

        public String getInfo() {
            return info;
        }

        public String getDesc() {
            return desc;
        }

        public int getPhoto() {
            return photo;
        }

        public double getPrice() {
            return price;
        }
    }

    private ArrayList<RoomType> roomTypes;

    public Hotel(String name, String description, String address, String phone, String website, String open_time, String close_time, double lat, double lng) {
        super(name, description, address, phone, website, open_time, close_time, lat, lng);
        roomTypes = new ArrayList<>();
    }

    public Hotel(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
        roomTypes = new ArrayList<RoomType>();
        try {
            JSONArray jsonArray = jsonObject.getJSONObject(Hotel.TAG_ROOMTYPES).getJSONArray(Hotel.TAG_ROOMTYPE);

            for (int i = 0; i < jsonArray.length(); ++i) {
                roomTypes.add(new RoomType(context, jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    /*
     private static final String TAG_ROOMNAME = "rname";
    private static final String TAG_INFO = "info";
    private static final String TAG_DESC = "desc";
    private static final String TAG_PHOTO = "photo";
    private final static String TAG_PRICE = "price";
     */
    @Override
    public void writeToJSON(JsonWriter jsonWriter) {
        super.writeToJSON(jsonWriter);
        try {
            jsonWriter.name(TAG_ROOMTYPES).beginObject();
            jsonWriter.name(TAG_ROOMTYPE).beginArray();
            for (int i = 0; i < roomTypes.size(); ++i) {
                jsonWriter.beginObject();
                jsonWriter.name(TAG_ROOMNAME).value(roomTypes.get(i).name);
                jsonWriter.name(TAG_INFO).value(roomTypes.get(i).info);
                jsonWriter.name(TAG_DESC).value(roomTypes.get(i).desc);
                jsonWriter.name(TAG_PHOTO).value(roomTypes.get(i).photo);
                jsonWriter.name(TAG_PRICE).value(roomTypes.get(i).price);
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
