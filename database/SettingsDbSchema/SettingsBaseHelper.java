package com.bignerdranch.android.triplogger.database.SettingsDbSchema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.triplogger.database.TripDbSchema.TripDbSchema;
import com.bignerdranch.android.triplogger.settings.Settings;

import static com.bignerdranch.android.triplogger.database.SettingsDbSchema.SettingsDbSchema.*;


public class SettingsBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private static final String SETTINGS_DATABASE_NAME = "settingsBase.db";
    public SettingsBaseHelper(Context context){
        super(context, SETTINGS_DATABASE_NAME, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sdb){
        sdb.execSQL("create table" + SettingsTable.SET + "(" +
                " _id integer primary key autoincrement, " +
                SettingsTable.Cols.UUID + ", " +
                SettingsTable.Cols.NAME + ", " +
                SettingsTable.Cols.ID + ", " +
                SettingsTable.Cols.EMAIL + ", " +
                SettingsTable.Cols.GENDER + ", " +
                SettingsTable.Cols.COMMENT +
                ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
