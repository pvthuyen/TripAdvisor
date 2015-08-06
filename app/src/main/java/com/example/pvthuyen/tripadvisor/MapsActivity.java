package com.example.pvthuyen.tripadvisor;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapsActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback{

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private double currentLatitude;
    private double currentLongitude;

    private TreeMap<String, Integer> categoryMap;
    private TreeMap<String, Integer> placeMap;

    private Marker chosenMarker;

    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        ViewGroup appViewGroup = (ViewGroup) inflater.inflate(R.layout.activity_maps, null);
        setContentView(R.layout.activity_maps);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            // fall back to network if GPS is not available
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
        }

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.fgMap);
        mapFragment.getMapAsync(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        googleMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onMapLoaded() {
        ArrayList<Hotel> hotels = Global.hotelList;
        ArrayList<Restaurant> restaurants = Global.restaurantList;
        ArrayList<Shop> shops = Global.shopList;
        ArrayList<Park> parks = Global.parkList;
        ArrayList<Theater> theaters = Global.theaterList;

        categoryMap = new TreeMap<>();
        placeMap = new TreeMap<>();

        for(int i = 0; i < hotels.size(); ++i){
            Hotel hotel = hotels.get(i);
            map.addMarker(new MarkerOptions().position(new LatLng(hotel.getLat(), hotel.getLng()))
                    .title(hotel.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.hotel_icon)));
            categoryMap.put(hotel.getName(), 0);
            placeMap.put(hotel.getName(), i);
        }

        for(int i = 0; i < restaurants.size(); ++i){
            Restaurant restaurant = restaurants.get(i);
            map.addMarker(new MarkerOptions().position(new LatLng(restaurant.getLat(), restaurant.getLng()))
                    .title(restaurant.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.res_icon)));
            categoryMap.put(restaurant.getName(), 2);
            placeMap.put(restaurant.getName(), i);
        }

        for(int i = 0; i < shops.size(); ++i){
            Shop shop = shops.get(i);
            map.addMarker(new MarkerOptions().position(new LatLng(shop.getLat(), shop.getLng()))
                    .title(shop.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_icon)));
            categoryMap.put(shop.getName(), 3);
            placeMap.put(shop.getName(), i);
        }

        for(int i = 0; i < parks.size(); ++i){
            Park park = parks.get(i);
            map.addMarker(new MarkerOptions().position(new LatLng(park.getLat(), park.getLng()))
                    .title(park.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.park_icon)));
            categoryMap.put(park.getName(), 1);
            placeMap.put(park.getName(), i);
        }

        for (int i = 0; i < theaters.size(); ++i) {
            Theater theater = theaters.get(i);
            map.addMarker(new MarkerOptions().position(new LatLng(theater.getLat(), theater.getLng()))
                    .title(theater.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.theater_icon)));
            categoryMap.put(theater.getName(), 4);
            placeMap.put(theater.getName(), i);
        }

        map.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Position"));

//        if(startLocation == 0) {
//            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLatitude, currentLongitude)));
//        }
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(currentLatitude, currentLongitude)));
        map.animateCamera(CameraUpdateFactory.zoomTo(14));
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ImageView imageView = new ImageView(MapsActivity.this);

                MapsActivity.this.addContentView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                popupMenu = new PopupMenu(MapsActivity.this, imageView);

                //Inflating the Popup using xml file
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                chosenMarker = marker;

                //registering popup with OnMenuItemClickListener
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.itShowDetail) {
                            Intent intent = new Intent(MapsActivity.this, DetailActivity.class);
                            intent.putExtra("category", categoryMap.get(chosenMarker.getTitle()));
                            intent.putExtra("place", placeMap.get(chosenMarker.getTitle()));
                            startActivity(intent);
                        } else {
                            LatLng origin = new LatLng(currentLatitude, currentLongitude);
                            LatLng dest = new LatLng(chosenMarker.getPosition().latitude, chosenMarker.getPosition().longitude);

                            // Getting URL to the Google Directions API
                            String url = getDirectionsUrl(origin, dest);

                            DownloadTask downloadTask = new DownloadTask();

                            // Start downloading json data from Google Directions API
                            downloadTask.execute(url);
                        }
                        popupMenu.dismiss();
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(MapsActivity.this, NewPlaceActivity.class);
                intent.putExtra("lat", latLng.latitude);
                intent.putExtra("lng", latLng.longitude);
                startActivity(intent);
            }
        });
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }
}
