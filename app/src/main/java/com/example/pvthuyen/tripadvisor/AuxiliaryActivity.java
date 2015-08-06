package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class AuxiliaryActivity extends Activity {

    private int chosenCategory;
    private int chosenPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auxiliary);

        chosenCategory = getIntent().getIntExtra("category", 0);
        chosenPlace = getIntent().getIntExtra("place", 0);

        ListView listViewAuxiliary = (ListView) findViewById(R.id.lvAuxiliary);

        switch (chosenCategory) {
            case 0: {
                Hotel hotel = Global.hotelList.get(chosenPlace);
                AuxiliaryAdapter auxiliaryAdapter = new AuxiliaryAdapter(this, new String[hotel.getRoomTypes().size()], hotel.getRoomTypes(), true);
                listViewAuxiliary.setAdapter(auxiliaryAdapter);
                break;
            }
            case 2: {
                Restaurant restaurant = Global.restaurantList.get(chosenPlace);
                AuxiliaryAdapter auxiliaryAdapter = new AuxiliaryAdapter(this, new String[restaurant.getMenu().size()], restaurant.getMenu(), 'a');
                listViewAuxiliary.setAdapter(auxiliaryAdapter);
                break;
            }
            case 3: {
                Shop shop = Global.shopList.get(chosenPlace);
                AuxiliaryAdapter auxiliaryAdapter = new AuxiliaryAdapter(this, new String[shop.getProducts().size()], shop.getProducts());
                listViewAuxiliary.setAdapter(auxiliaryAdapter);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auxiliary, menu);
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
}
