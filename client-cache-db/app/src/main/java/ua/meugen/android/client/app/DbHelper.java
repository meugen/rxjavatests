package ua.meugen.android.client.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbHelper extends SQLiteOpenHelper {

    private static final String NAME = "client.db";
    private static final int VERSION = 1;

    @Inject
    public DbHelper(final Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items (" +
                " id INTEGER NOT NULL PRIMARY KEY," +
                " item VARCHAR(50) NOT NULL);");
    }

    @Override
    public void onUpgrade(
            final SQLiteDatabase db,
            final int oldVersion,
            final int newVersion) {
        db.execSQL("DROP TABLE items;");
        onCreate(db);
    }
}
