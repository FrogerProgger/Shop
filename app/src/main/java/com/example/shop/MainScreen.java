package com.example.shop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shop.Additionals.DBHelper;
import com.example.shop.Additionals.Product;

import java.util.List;

public class MainScreen extends AppCompatActivity {
    private TextView tryTextView;
    SQLiteDatabase db;
    List<Product> productList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_screen);

        tryTextView = findViewById(R.id.tryTextView);
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Products", null);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int categoryIdIndex = cursor.getColumnIndex("category_id");
            int imageIdIndex = cursor.getColumnIndex("image_id");
            int priceIndex = cursor.getColumnIndex("price");
            int descriptionIndex = cursor.getColumnIndex("description");
            do {
                productList.add(new Product(cursor.getString(nameIndex), cursor.getInt(categoryIdIndex), cursor.getInt(imageIdIndex), cursor.getDouble(priceIndex), cursor.getString(descriptionIndex)));
            } while (cursor.moveToNext());
        }

        tryTextView.setText(productList.get(0).getDescription());

    }
}
