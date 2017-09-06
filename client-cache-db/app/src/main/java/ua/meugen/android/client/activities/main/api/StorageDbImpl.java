package ua.meugen.android.client.activities.main.api;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import ua.meugen.android.client.app.DbHelper;

public class StorageDbImpl implements StorageDb {

    private final DbHelper helper;

    @Inject
    public StorageDbImpl(final DbHelper helper) {
        this.helper = helper;
    }

    @Override
    public void storeItems(final List<String> items) {
        final SQLiteDatabase db = helper.getWritableDatabase();

        db.beginTransaction();
        try {
            db.delete("items", null, null);
            for (String item : items) {
                final ContentValues values = new ContentValues();
                values.put("item", item);
                db.insertOrThrow("items", null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public List<String> loadItems() {
        final SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT item FROM items;", null);
            final List<String> items = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                items.add(cursor.getString(0));
            }
            return items;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }
}
