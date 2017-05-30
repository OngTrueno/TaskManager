package com.example.a15017470.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017470 on 30/5/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Task";
    private static final String COL_ID = "_id";
    private static final String COL1 = "name";
    private static final String COL2 = "desc";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE Task(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, desc TEXT )";
        db.execSQL(createNoteTableSql);
        Log.i("info", createNoteTableSql + "\ncreated tables");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Task");

        onCreate(db);
    }

    public long insertTask(String name, String desc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1, name);
        values.put(COL2, desc);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("SQL Insert", BuildConfig.FLAVOR + result);
        return result;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskslist = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id,name,desc FROM Task", null);
        if (cursor.moveToFirst()) {
            do {
                taskslist.add(new Task(cursor.getInt(0), cursor.getString(DATABASE_VERSION), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskslist;
    }
}
