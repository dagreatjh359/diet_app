package com.example.diet_app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.diet_app.MyContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/foods";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String _ID = "_id";
    static final String date = "date";
    static final String location = "location";
    static final String review = "review";
    static final String food1 = "food1";
    static final String calorie1 = "calorie1";
    static final String quantity1 = "quantity1";
    static final String food2 = "food2";
    static final String calorie2 = "calorie2";
    static final String quantity2 = "quantity2";
    static final String food3 = "food3";
    static final String calorie3 = "calorie3";
    static final String quantity3 = "quantity3";
    static final String photo1 = "photo1";
    static final String photo2 = "photo2";

    public DBManager dbManager;
    public MyContentProvider() {
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return dbManager.delete(selection, selectionArgs);
    }
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowid = dbManager.insert(values);
        return null;
    }
    @Override
    public boolean onCreate() {
        dbManager = DBManager.getInstance(getContext());
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return dbManager.query(projection, selection, selectionArgs, null,
                null, sortOrder);
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}