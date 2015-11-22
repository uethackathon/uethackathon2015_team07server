package com.uet.quantity.uethackathon2015_team7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uet.quantity.uethackathon2015_team7.model.HistoryItem;

/**
 * Created by luongnguyen on 11/21/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "HistoryDatabase";

    // Contacts table name
    private static final String TABLE_HISTORY = "history";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_YEAR = "year";
    private static final String KEY_DAY_MONTH = "day_month";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_MAIN_EVENT = "main_event";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_YEAR + " TEXT,"
                + KEY_DAY_MONTH + " TEXT," + KEY_CONTENT + " TEXT," + KEY_MAIN_EVENT + " BOOLEAN" +  ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);

        // Create tables again
        onCreate(db);
    }

    public void addHistory(HistoryItem historyItem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, historyItem.getId());
        values.put(KEY_YEAR, historyItem.getYear());
        values.put(KEY_CONTENT, historyItem.getContent());
        values.put(KEY_DAY_MONTH, historyItem.getDay_month());
        values.put(KEY_MAIN_EVENT, historyItem.isMain_event());


        // Inserting Row
        db.insert(TABLE_HISTORY, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public HistoryItem getHistory(String day_month) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[] { KEY_ID,
                        KEY_YEAR, KEY_DAY_MONTH, KEY_CONTENT, KEY_MAIN_EVENT }, KEY_DAY_MONTH + "=?",
                new String[] {day_month}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // return content
        return new HistoryItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Boolean.parseBoolean(cursor.getString(4)));
    }

    // Getting single contact
    public HistoryItem get(int i) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HISTORY, new String[] { KEY_ID,
                        KEY_YEAR, KEY_DAY_MONTH, KEY_CONTENT, KEY_MAIN_EVENT }, KEY_ID + "=?",
                new String[] {i + ""}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // return content
        return new HistoryItem(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Boolean.parseBoolean(cursor.getString(4)));
    }

    public int getHistoryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_HISTORY;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }
}
