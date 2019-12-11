package com.macom.medicationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "MEDICATION_REMINDER";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TIME = "time";
    static final int DB_VERSION = 1;
    static final String DB_NAME = "REMIDER_LIST";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REMINDER_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY," + TITLE + " TEXT," + DESCRIPTION + " TEXT," + TIME + " TEXT" + ")";

        db.execSQL(CREATE_REMINDER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    void addReminder(MedicinReminderModel reminderModel) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, reminderModel.getTitle());
        values.put(DESCRIPTION, reminderModel.getDescription());
        values.put(TIME, reminderModel.getTime());

        database.insert(TABLE_NAME, null, values);
        database.close();

    }

    List<MedicinReminderModel> getAllReminders() {

        String SELECT_QUERY = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        List<MedicinReminderModel> reminderModelList = new ArrayList<>();
        Cursor cursor = database.rawQuery(SELECT_QUERY, null);

        if (cursor.moveToFirst()) {


            MedicinReminderModel medicinReminderModel = new MedicinReminderModel();
            medicinReminderModel.setTitle(cursor.getString(1));
            medicinReminderModel.setDescription(cursor.getString(2));
            medicinReminderModel.setTime(cursor.getString(3));
            reminderModelList.add(medicinReminderModel);
        }


        return reminderModelList;

    }
}