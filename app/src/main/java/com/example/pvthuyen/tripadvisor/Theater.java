package com.example.pvthuyen.tripadvisor;

import android.content.Context;
import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by pvthuyen on 8/2/15.
 */
public class Theater extends Place {
    private static final String TAG_TICKETPRICE = "ticket_price";

    private int ticketPrice;

    public Theater(String name, String description, String address, String phone, String website, String open_time, String close_time, double lat, double lng) {
        super(name, description, address, phone, website, open_time, close_time, lat, lng);
    }

    public Theater(Context context, JSONObject jsonObject) {
        super(context, jsonObject);
        try {
            this.ticketPrice = jsonObject.getInt(Theater.TAG_TICKETPRICE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public void writeToJSON(JsonWriter jsonWriter) {
        super.writeToJSON(jsonWriter);
        try {
            jsonWriter.name(TAG_TICKETPRICE).value(ticketPrice);
            jsonWriter.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
