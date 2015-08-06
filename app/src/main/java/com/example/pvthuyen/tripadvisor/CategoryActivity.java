package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class CategoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
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

    public void onClickHotel(View view) {
        int chosenCategory = 0;
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("chosen", chosenCategory);
        startActivity(intent);
    }

    public void onClickShop(View view) {
        int chosenCategory = 3;
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("chosen", chosenCategory);
        startActivity(intent);
    }

    public void onClickRestaurant(View view) {
        int chosenCategory = 2;
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("chosen", chosenCategory);
        startActivity(intent);
    }

    public void onClickPark(View view) {
        int chosenCategory = 1;
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("chosen", chosenCategory);
        startActivity(intent);
    }

    public void onClickTheater(View view) {
        int chosenCategory = 4;
        Intent intent = new Intent(this, PlaceListActivity.class);
        intent.putExtra("chosen", chosenCategory);
        startActivity(intent);
    }
}
