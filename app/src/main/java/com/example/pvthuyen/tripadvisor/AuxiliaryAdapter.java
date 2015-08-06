package com.example.pvthuyen.tripadvisor;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pvthuyen.tripadvisor.Hotel;
import com.example.pvthuyen.tripadvisor.R;
import com.example.pvthuyen.tripadvisor.Restaurant;
import com.example.pvthuyen.tripadvisor.Shop;

import java.util.ArrayList;

public class AuxiliaryAdapter extends ArrayAdapter<String>{
    private Activity context;
    private String[] itemname;
    private ArrayList<Integer> imgid = new ArrayList<Integer>();


    //hotel
    public AuxiliaryAdapter(Activity context,String[] dummy, ArrayList<Hotel.RoomType> roomTypes, boolean nothing) {
        super(context, R.layout.auxiliary_list_layout, dummy);
        ArrayList<String> mItemName = new ArrayList<String>();
        for(int i = 0; i < roomTypes.size(); ++i) {
            String mItem = "Name: " + roomTypes.get(i).getName() + "\n" +
                    "Accommodation Information: " + roomTypes.get(i).getInfo() + "\n" +
                    "Description: " + roomTypes.get(i).getDesc() + "\n" +
                    "Price (per day): " + roomTypes.get(i).getPrice();
            mItemName.add(mItem);
            this.imgid.add(roomTypes.get(i).getPhoto());
        }

        this.context = context;
        this.itemname = mItemName.toArray(new String[mItemName.size()]);
    }

    //retaurant
    public AuxiliaryAdapter(Activity context,String[] dummy, ArrayList<Restaurant.Dish> dishes, char nothing) {
        super(context, R.layout.auxiliary_list_layout, dummy);
        ArrayList<String> mItemName = new ArrayList<String>();
        for(int i = 0; i < dishes.size(); ++i) {
            String mItem = "Name: " + dishes.get(i).getDishName() + "\n" +
                    "Description: " + dishes.get(i).getDescription() + "\n" +
                    "Price: " + dishes.get(i).getPrice();
            mItemName.add(mItem);
            this.imgid.add(dishes.get(i).getPhoto());
        }

        this.context = context;
        this.itemname = mItemName.toArray(new String[mItemName.size()]);
    }

    //shop
    public AuxiliaryAdapter(Activity context,String[] dummy, ArrayList<Shop.Product> products) {
        super(context, R.layout.auxiliary_list_layout, dummy);
        ArrayList<String> mItemName = new ArrayList<String>();
        for(int i = 0; i < products.size(); ++i) {
            String mItem = "Name: " + products.get(i).getProductName() + "\n" +
                    "Description: " + products.get(i).getDescription() + "\n";
            mItemName.add(mItem);
            this.imgid.add(products.get(i).getPhoto());
        }

        this.context = context;
        this.itemname = mItemName.toArray(new String[mItemName.size()]);
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.auxiliary_list_layout, null,true);

        TextView text = (TextView) rowView.findViewById(R.id.list_row_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_row_image);
        text.setText(itemname[position]);
        imageView.setImageResource(imgid.get(position));

        return rowView;
    }
}
