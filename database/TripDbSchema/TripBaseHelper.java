package com.bignerdranch.android.triplogger.database.TripDbSchema;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.triplogger.TripLab;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripDbSchema.TripTable;

import java.util.List;


public class TripBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION =1;
    private static final String DATABASE_NAME = "tripBase.db";
    private List<String> mAllLabels;

    public TripBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table" + TripTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                TripTable.Cols.UUID + ", " +
                TripTable.Cols.TITLE + ", " +
                TripTable.Cols.DATE + ", " +
                TripTable.Cols.COMMENT + ", " +
                TripTable.Cols.DURATION + ", " +
                TripTable.Cols.DESTINATION + ", " +
                TripTable.Cols.TRIP_TYPE +
                ")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
