package com.example.supermarket_checkout;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.supermarket_checkout.Database.OrderContract;

public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting theviews

        TextView Name, price, quantity;


        Name = view.findViewById(R.id.NameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);

        // getting the values by first getting the position of their columns

        int name_ = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int price_ = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantity_ = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

        String name__ = cursor.getString(name_);
        String price__ = cursor.getString(price_);
        String quantity__ = cursor.getString(quantity_);

        Name.setText(name__);
        price.setText(price__);
        quantity.setText(quantity__);

    }
}
