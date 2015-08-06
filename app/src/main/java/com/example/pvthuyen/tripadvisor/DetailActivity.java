package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import java.net.URI;


public class DetailActivity extends Activity {

    private int chosenCategory;
    private int chosenPlace;

    private TextView textViewWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        chosenCategory = getIntent().getIntExtra("category", 0);
        chosenPlace = getIntent().getIntExtra("place", 0);

        Place place = null;
        switch (chosenCategory) {
            case 0: {
                place = Global.hotelList.get(chosenPlace);
                TextView textViewAuxiliary = (TextView) findViewById(R.id.tvAuxiliary);
                textViewAuxiliary.setText("Tap to show room types");
                textViewAuxiliary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, AuxiliaryActivity.class);
                        intent.putExtra("category", chosenCategory);
                        intent.putExtra("place", chosenPlace);
                        startActivity(intent);
                    }
                });
                break;
            }
            case 1: {
                place = Global.parkList.get(chosenPlace);
                TextView textViewAuxiliary = (TextView) findViewById(R.id.tvAuxiliary);
                textViewAuxiliary.setText("Ticket Price: " + ((Park) place).getTicketPrice());
                break;
            }
            case 2: {
                place = Global.restaurantList.get(chosenPlace);
                TextView textViewAuxiliary = (TextView) findViewById(R.id.tvAuxiliary);
                textViewAuxiliary.setText("Tap to show menu");
                textViewAuxiliary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, AuxiliaryActivity.class);
                        intent.putExtra("category", chosenCategory);
                        intent.putExtra("place", chosenPlace);
                        startActivity(intent);
                    }
                });
                break;
            }
            case 3: {
                place = Global.shopList.get(chosenPlace);
                TextView textViewAuxiliary = (TextView) findViewById(R.id.tvAuxiliary);
                textViewAuxiliary.setText("Tap to show product list");
                textViewAuxiliary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, AuxiliaryActivity.class);
                        intent.putExtra("category", chosenCategory);
                        intent.putExtra("place", chosenPlace);
                        startActivity(intent);
                    }
                });
                break;
            }
            case 4: {
                place = Global.theaterList.get(chosenPlace);
                TextView textViewAuxiliary = (TextView) findViewById(R.id.tvAuxiliary);
                textViewAuxiliary.setText("Ticket Price: " + ((Theater) place).getTicketPrice());
                break;
            }
        }

        TextView textViewName = (TextView) findViewById(R.id.tvAttractionName);
        textViewName.setText(place.getName());

        TextView textViewAddress = (TextView) findViewById(R.id.tvAddress);
        textViewAddress.setText(place.getAddress());

        TextView textViewPhone = (TextView) findViewById(R.id.tvPhone);
        textViewPhone.setText(place.getPhone());

        textViewWebsite = (TextView) findViewById(R.id.tvWebsite);
        textViewWebsite.setText(place.getWebsite());
        textViewWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickWebsite(view);
            }
        });

        TextView textViewDescription = (TextView) findViewById(R.id.tvDescriptionAndTime);
        textViewDescription.setText(place.getDescription() + "\n" +
                "Opening Time: " + place.getOpen_time() + "\n" +
                "Closing Time: " + place.getClose_time());

        ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse(place.getWebsite()))
                .setContentTitle("I was here!").setContentDescription("I was here!").build();

        ShareButton shareButton = (ShareButton)findViewById(R.id.shareButton);
        shareButton.setShareContent(content);

        ImageView imageView = (ImageView) findViewById(R.id.ivPhoto);
        imageView.setImageResource(place.getPhoto());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    public void onClickWebsite(View view) {
        if(!textViewWebsite.getText().toString().equals("")) {
            String url = textViewWebsite.getText().toString();
            try {
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
            catch(ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickFavourite(View view) {
        Global.favorite.add(new Pair<>(chosenCategory, chosenPlace));
        Global.writeToJSONFiles(getApplicationContext());
        Toast.makeText(this, "Added to favorite", Toast.LENGTH_LONG).show();
    }
}
