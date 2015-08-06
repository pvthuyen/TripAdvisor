package com.example.pvthuyen.tripadvisor;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by pvthuyen on 8/3/15.
 */
public class Global {
    public static ArrayList<Hotel> hotelList = new ArrayList<>();
    public static ArrayList<Restaurant> restaurantList = new ArrayList<>();
    public static ArrayList<Park> parkList = new ArrayList<>();
    public static ArrayList<Shop> shopList = new ArrayList<>();
    public static ArrayList<Theater> theaterList = new ArrayList<>();
    public static ArrayList<Pair<Integer, Integer>> favorite = new ArrayList<>();

    public static void writeToJSONFiles(Context context) {
        try {
            File outputFile = new File(context.getFilesDir(), "hotel.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("hotels").beginObject();
            jsonWriter.name("hotel").beginArray();
            for (int i = 0; i < hotelList.size(); ++i) {
                hotelList.get(i).writeToJSON(jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File outputFile = new File(context.getFilesDir(), "restaurant.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("restaurants").beginObject();
            jsonWriter.name("restaurant").beginArray();
            for (int i = 0; i < restaurantList.size(); ++i) {
                restaurantList.get(i).writeToJSON(jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        try {
            File outputFile = new File(context.getFilesDir(), "park.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("parks").beginObject();
            jsonWriter.name("park").beginArray();
            for (int i = 0; i < parkList.size(); ++i) {
                parkList.get(i).writeToJSON(jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File outputFile = new File(context.getFilesDir(), "shop.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("shops").beginObject();
            jsonWriter.name("shop").beginArray();
            for (int i = 0; i < shopList.size(); ++i) {
                shopList.get(i).writeToJSON(jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File outputFile = new File(context.getFilesDir(), "theater.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("theaters").beginObject();
            jsonWriter.name("theater").beginArray();
            for (int i = 0; i < theaterList.size(); ++i) {
                theaterList.get(i).writeToJSON(jsonWriter);
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File outputFile = new File(context.getFilesDir(), "favorite.json");
            JsonWriter jsonWriter = new JsonWriter(new FileWriter(outputFile));
            jsonWriter.beginObject();
            jsonWriter.name("favorites").beginObject();
            jsonWriter.name("favorite").beginArray();
            Pair <Integer, Integer> favorite[] = new Pair[Global.favorite.size()];
            Global.favorite.toArray(favorite);
            for (int i = 0; i < Global.favorite.size(); ++i) {
                jsonWriter.beginObject();
                jsonWriter.name("category").value(favorite[i].first);
                jsonWriter.name("place").value(favorite[i].second);
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
