package com.example.supermarket_checkout;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.supermarket_checkout.Database.OrderContract;

public class CheckenActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    // first of all we will get the views that are  present in the layout of info

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, Name, Price;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        Name = findViewById(R.id.NameinInfo);
        Price = findViewById(R.id.Price);
        addtoCart = findViewById(R.id.addtocart);

        // setting the name of drink
        Price.setText("110");
        Name.setText("Freeze Chicken");
        imageView.setImageResource(R.drawable.checken);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckenActivity.this, SummaryActivity.class);
                startActivity(intent);
                // once this button is clicked we want to save our values in the database and send those values
                // right away to summary activity where we display the order info
                SaveCart();

            }
        });

        int BasePrice= Integer.parseInt(Price.getText().toString());


        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //price
                quantity++;
                displayQuantity();
                int AllPrice= BasePrice * quantity;
                String setnewPrice = String.valueOf(AllPrice);
                Price.setText(setnewPrice);
            }
        });
        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //price
                if (quantity == 0) {
                    Toast.makeText(CheckenActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int AllPrice= BasePrice * quantity;
                    String setnewPrice = String.valueOf(AllPrice);
                    Price.setText(setnewPrice);
                }

            }
        });

    }
    private boolean SaveCart() {
        // getting the values from our views
        if(quantity==0){
            Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            String name = Name.getText().toString();
            String price = Price.getText().toString();
            String quantity = quantitynumber.getText().toString();
            ContentValues values = new ContentValues();
            values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
            values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
            values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);
            if (mCurrentCartUri == null) {
                Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
                if (newUri == null) {
                    Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();
                }
            }
            hasAllRequiredValues = true;
            return hasAllRequiredValues;
        }
    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY
        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);
            String name_ = cursor.getString(name);
            String price_ = cursor.getString(price);
            String quantity_ = cursor.getString(quantity);
            Name.setText(name_);
            Price.setText(price_);
            quantitynumber.setText(quantity_);
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Name.setText("");
        Price.setText("");
        quantitynumber.setText("");
    }

}
