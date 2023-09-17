package com.example.shop.Additionals;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shop.R;

public class DBHelper extends SQLiteOpenHelper {

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "FroggerShop";

    private SQLiteDatabase database;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            // Проверяем, существует ли уже база данных. Если да, инициализируем объект database существующей базой данных.
            if (db != null) {
                database = db;
            } else {
                // Если база данных не существует, создаем новую базу данных и инициализируем объект database.
                database = getWritableDatabase();
            }

            // Выполняем операции создания таблиц.
            database.execSQL("CREATE TABLE IF NOT EXISTS Categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");

            database.execSQL("CREATE TABLE IF NOT EXISTS Products (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "name TEXT, " +
                            "category_id INTEGER, " +
                            "price REAL, " +
                            "image_id INTEGER, " +
                            "description TEXT," +
                            "FOREIGN KEY(category_id) REFERENCES Categoties(id))");

        // Создаю таблицу промокодов.
        database.execSQL("CREATE TABLE IF NOT EXISTS Promocods (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name Text, " +
                "category_id INTEGER, " +
                "discount INTEGER, " +
                "FOREIGN KEY(category_id) REFERENCES Categoties(id))"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Categories");
        db.execSQL("DROP TABLE IF EXISTS Products");
        db.execSQL("DROP TABLE IF EXISTS Promocods");
        onCreate(db);
    }

    public void dropTables() {
        database.execSQL("DROP TABLE IF EXISTS Categories");
        database.execSQL("DROP TABLE IF EXISTS Products");
        database.execSQL("DROP TABLE IF EXISTS Promocods");
    }

    public void firstCreate() {
        String insertCategoriesQuery = "INSERT INTO Categories (name) VALUES ('Продукты'), ('Компьютеры'), ('Жабки'), ('Игры')";
        database.execSQL(insertCategoriesQuery);
    }

    public void insertProduct(Product product, String tableName){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", product.getName());
        contentValues.put("image_id", product.getImageId());
        contentValues.put("price", product.getPrice());
        contentValues.put("category_id", product.getCategoryId());
        contentValues.put("description", product.getDescription());

        database.insert(tableName, null, contentValues);
    }
}

