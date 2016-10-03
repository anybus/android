package demo.fragmentui.com.basicfragment001.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anybus on 16/9/30.
 */
public class DB extends SQLiteOpenHelper {
    public DB(Context context) {
        super(context, "db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT ," +
                "sex TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
