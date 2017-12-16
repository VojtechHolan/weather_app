package com.example.holas.weather_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Holas on 27.11.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Weather_db";

    // Contacts table name
    private static final String TABLE_CITY = " city";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_CITY = "name";
    private static final String KEY_LON = "lon";
    private static final String KEY_LAT = "lat";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_ID   + " INTEGER PRIMARY KEY,"
                + KEY_CITY + " TEXT,"
                + KEY_LON  + " TEXT,"
                + KEY_LAT  + " TEXT" + ")";
        db.execSQL(CREATE_CITY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);

        // Create tables again
        onCreate(db);
    }

    public void resetCityTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);

        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY + "("
                + KEY_ID   + " INTEGER PRIMARY KEY,"
                + KEY_CITY + " TEXT,"
                + KEY_LON  + " TEXT,"
                + KEY_LAT  + " TEXT" + ")";
        db.execSQL(CREATE_CITY_TABLE);
        db.close();
    }

    // Adding new contact
    public void addCity(City city) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CITY, city.getName());
        values.put(KEY_LAT, city.getLat());
        values.put(KEY_LON, city.getLon());

        // Inserting Row
        db.insert(TABLE_CITY, null, values);
        db.close(); // Closing database connection
    }

    public City getCity(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CITY, new String[] { KEY_ID,
                        KEY_CITY, KEY_LON, KEY_LAT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        City city = new City(cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return city;
    }

}