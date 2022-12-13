package com.example.diet_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    static final String FOOD_DB = "Food.db";
    static final String FOOD_TABLE = "Food";
    Context context = null;
    private static DBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + FOOD_TABLE + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " date TEXT NOT NULL, location TEXT NOT NULL, review TEXT, " +
            "food1 TEXT, calorie1 TEXT, quantity1 TEXT," +
            "food2 TEXT, calorie2 TEXT, quantity2 TEXT," +
            "food3 TEXT, calorie3 TEXT, quantity3 TEXT," +
            "food4 TEXT, calorie4 TEXT, quantity4 TEXT," +
            "food5 TEXT, calorie5 TEXT, quantity5 TEXT," +
            "photo1 TEXT, photo2 TEXT);";

    public static DBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new DBManager(context, FOOD_DB, null, 1);
        }
        return dbManager;
    }
    public DBManager(@Nullable Context context, @Nullable String dbName,
                     @Nullable SQLiteDatabase.CursorFactory factory, @Nullable int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }

    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(FOOD_TABLE, null, addValue);
    }
    public Cursor query(String [] columns, String selection, String[]
            selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(FOOD_TABLE, columns,
                selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(FOOD_TABLE, whereClause,
                whereArgs);
    }

}