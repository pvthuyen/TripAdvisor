package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class PlaceListActivity extends Activity {
    private int placeType;
    private ListView placeList;
    private EditText inputSearch;

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        Intent intent = getIntent();
        placeType = intent.getIntExtra("chosen", 0);

        placeList = (ListView) findViewById(R.id.attraction_lv);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        final ArrayList<String> places = new ArrayList<String>();
        switch (placeType) {
            case 0:
                for (int i = 0; i < Global.hotelList.size(); ++i) {
                    places.add((Global.hotelList.get(i).getName()));
                }
                break;
            case 1:
                for (int i = 0; i < Global.parkList.size(); ++i) {
                    places.add((Global.parkList.get(i).getName()));
                }
                break;
            case 2:
                for (int i = 0; i < Global.restaurantList.size(); ++i) {
                    places.add((Global.restaurantList.get(i).getName()));
                }
                break;
            case 3:
                for (int i = 0; i < Global.shopList.size(); ++i) {
                    places.add((Global.shopList.get(i).getName()));
                }
                break;
            case 4:
                for (int i = 0; i < Global.theaterList.size(); ++i) {
                    places.add((Global.theaterList.get(i).getName()));
                }
                break;
        }
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item, R.id.place_name, places);
        placeList.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        placeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "have " + ((MyApplication) getApplicationContext()).parkList.size(),Toast.LENGTH_LONG).show();
                showItem(places.indexOf(adapter.getItem(position)));
            }
        });
    }

    private void showItem(int i) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("category", placeType);
        intent.putExtra("place", i);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_place_list, menu);
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
