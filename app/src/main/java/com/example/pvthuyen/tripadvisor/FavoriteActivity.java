package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class FavoriteActivity extends Activity {

    private Pair<Integer, Integer> favorite[];
    private ArrayAdapter <String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        final ArrayList<String> places = new ArrayList<String>();

        favorite = new Pair[Global.favorite.size()];
        Global.favorite.toArray(favorite);

        ListView placeList = (ListView) findViewById(R.id.attraction_lv);


        for (int i = 0; i < Global.favorite.size(); ++i) {
            switch (favorite[i].first) {
                case 0:
                    places.add((Global.hotelList.get(favorite[i].second).getName()));
                    break;
                case 1:
                    places.add((Global.parkList.get(favorite[i].second).getName()));
                    break;
                case 2:
                    places.add((Global.restaurantList.get(favorite[i].second).getName()));
                    break;
                case 3:
                    places.add((Global.shopList.get(favorite[i].second).getName()));
                    break;
                case 4:
                    places.add((Global.theaterList.get(favorite[i].second).getName()));
                    break;
            }
        }
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.place_name, places);
        placeList.setAdapter(arrayAdapter);

        placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "have " + ((MyApplication) getApplicationContext()).parkList.size(),Toast.LENGTH_LONG).show();
                showItem(places.indexOf(arrayAdapter.getItem(position)));
            }
        });
    }

    private void showItem(int i) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("category", favorite[i].first);
        intent.putExtra("place", favorite[i].second);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
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
