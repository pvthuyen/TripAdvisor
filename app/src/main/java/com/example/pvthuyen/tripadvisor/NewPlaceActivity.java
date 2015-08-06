package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NewPlaceActivity extends Activity {

    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        lat = getIntent().getIntExtra("lat", 0);
        lng = getIntent().getIntExtra("lng", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_place, menu);
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

    public void onClickAdd(View view) {
        EditText editTextType = (EditText) findViewById(R.id.etType);
        EditText editTextName = (EditText) findViewById(R.id.etName);
        EditText editTextAddress = (EditText) findViewById(R.id.etAddress);
        EditText editTextPhone = (EditText) findViewById(R.id.etPhone);
        EditText editTextWebstie = (EditText) findViewById(R.id.etWebsite);
        EditText editTextDescription = (EditText) findViewById(R.id.etDescription);
        EditText editTextOpeningTime = (EditText) findViewById(R.id.etOpening);
        EditText editTextClosingTime = (EditText) findViewById(R.id.etClosing);

        String placeType = editTextType.getText().toString();
        String name = editTextName.getText().toString();
        String address = editTextAddress.getText().toString();
        String phone = editTextPhone.getText().toString();
        String website = editTextWebstie.getText().toString();
        String description = editTextDescription.getText().toString();
        String openingTime = editTextOpeningTime.getText().toString();
        String closingTime = editTextClosingTime.getText().toString();

        if (placeType.compareToIgnoreCase("hotel") == 0) {
            Hotel hotel = new Hotel(name, description, address, phone, website, openingTime, closingTime, lat, lng);
            Global.hotelList.add(hotel);
        }
        else if (placeType.compareToIgnoreCase("park") == 0) {
            Park park = new Park(name, description, address, phone, website, openingTime, closingTime, lat, lng);
            Global.parkList.add(park);
        }
        else if (placeType.compareToIgnoreCase("restaurant") == 0) {
            Restaurant restaurant = new Restaurant(name, description, address, phone, website, openingTime, closingTime, lat, lng);
            Global.restaurantList.add(restaurant);
        }
        else if (placeType.compareToIgnoreCase("shop") == 0) {
            Shop shop = new Shop(name, description, address, phone, website, openingTime, closingTime, lat, lng);
            Global.shopList.add(shop);
        }
        else if (placeType.compareToIgnoreCase("theater") == 0) {
            Theater theater = new Theater(name, description, address, phone, website, openingTime, closingTime, lat, lng);
            Global.theaterList.add(theater);
        }
        Global.writeToJSONFiles(getApplicationContext());
        this.finish();
    }
}
