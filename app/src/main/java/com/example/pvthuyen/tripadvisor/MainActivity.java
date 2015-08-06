package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.util.Printer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Scanner;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!fileExist("hotel.json"))
            copyJSONFilesToInternal();
        parseJSONFiles();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    private void parseJSONFiles() {
        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("hotel.json");
            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("hotels").getJSONArray("hotel");

            for (int i = 0; i < jsonArray.length(); ++i) {
                Global.hotelList.add(new Hotel(this, jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("park.json");

            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("parks").getJSONArray("park");

            for (int i = 0; i < jsonArray.length(); ++i) {
                Global.parkList.add(new Park(this, jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("restaurant.json");
            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("restaurants").getJSONArray("restaurant");

            for (int i = 0; i < jsonArray.length(); ++i) {
                Global.restaurantList.add(new Restaurant(this, jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("shop.json");
            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("shops").getJSONArray("shop");

            for (int i = 0; i < jsonArray.length(); ++i) {
                Global.shopList.add(new Shop(this, jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("theater.json");
            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("theaters").getJSONArray("theater");

            for (int i = 0; i < jsonArray.length(); ++i) {
                Global.theaterList.add(new Theater(this, jsonArray.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("favorite.json");
            Scanner scanner = new Scanner(fileInputStream);

            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                stringBuilder.append(readLine);
            }

            String jsonString = new String(stringBuilder);

            JSONObject jsonObject = new JSONObject(jsonString);

            JSONArray jsonArray = jsonObject.getJSONObject("favorites").getJSONArray("favorite");
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject favoriteObject = jsonArray.getJSONObject(i);
                int category = favoriteObject.getInt("category");
                int place = favoriteObject.getInt("place");
                Global.favorite.add(new Pair<>(category, place));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyJSONFilesToInternal() {

        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("raw/hotel",
                            "raw", getPackageName()));
            Scanner scanner = new Scanner(is);
            File outputFile = new File(getApplicationContext().getFilesDir(), "hotel.json");
            PrintStream printStream = new PrintStream(outputFile);

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                printStream.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("raw/park",
                            "raw", getPackageName()));
            Scanner scanner = new Scanner(is);
            File outputFile = new File(getApplicationContext().getFilesDir(), "park.json");
            PrintStream printStream = new PrintStream(outputFile);

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                printStream.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("raw/restaurant",
                            "raw", getPackageName()));
            Scanner scanner = new Scanner(is);
            File outputFile = new File(getApplicationContext().getFilesDir(), "restaurant.json");
            PrintStream printStream = new PrintStream(outputFile);

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                printStream.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("raw/shop",
                            "raw", getPackageName()));
            Scanner scanner = new Scanner(is);
            File outputFile = new File(getApplicationContext().getFilesDir(), "shop.json");
            PrintStream printStream = new PrintStream(outputFile);

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                printStream.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier("raw/theater",
                            "raw", getPackageName()));
            Scanner scanner = new Scanner(is);
            File outputFile = new File(getApplicationContext().getFilesDir(), "theater.json");
            PrintStream printStream = new PrintStream(outputFile);

            while (scanner.hasNext()) {
                String readLine = scanner.nextLine();
                printStream.println(readLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickCategory(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

    public void onClickNearby(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickShowFavourite(View view) {
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }
}
