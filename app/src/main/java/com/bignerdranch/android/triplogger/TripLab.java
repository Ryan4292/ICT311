package com.bignerdranch.android.triplogger;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.bignerdranch.android.triplogger.database.SettingsDbSchema.SettingsBaseHelper;
import com.bignerdranch.android.triplogger.database.SettingsDbSchema.SettingsCursorWrapper;
import com.bignerdranch.android.triplogger.database.SettingsDbSchema.SettingsDbSchema;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripBaseHelper;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripCursorWrapper;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripDbSchema;
import com.bignerdranch.android.triplogger.settings.Settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class TripLab {
    private static TripLab sTripLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteDatabase mSettingsDb;


    public static TripLab get(Context context){
        if(sTripLab == null){
            sTripLab = new TripLab(context);
        }
        return sTripLab;
    }
    private TripLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TripBaseHelper(mContext)
                .getWritableDatabase();
        mSettingsDb = new SettingsBaseHelper(mContext)
                .getWritableDatabase();

    }



    public void addTrip(Trip t){
        ContentValues values = getContentValues(t);
        mDatabase.insert(TripDbSchema.TripTable.NAME, null, values);
    }



    public List<Trip> getTrips(){
        List<Trip> trips = new ArrayList<>();
        try (TripCursorWrapper cursor = queryTrips(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                trips.add(cursor.getTrip());
                cursor.moveToNext();
            }
        }
        return trips;
    }

    public Trip getTrip(UUID id){
        try (TripCursorWrapper cursor = queryTrips(TripDbSchema.TripTable.Cols.UUID + " = ?", new String[]{id.toString()})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTrip();
        }
    }

    public Settings getSettings(UUID settingId){
        try (SettingsCursorWrapper cursor = querySettings(SettingsDbSchema.SettingsTable.Cols.UUID + " = ?", new String[]{settingId.toString()})) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSettings();
        }
    }
    public List<Settings> getSettings(){
        List<Settings> settings = new ArrayList<>();
        try (SettingsCursorWrapper cursor = querySettings(null, null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                settings.add(cursor.getSettings());
                cursor.moveToNext();
            }
        }
        return settings;
    }
    public void addSetting(Settings s){
        ContentValues values = getSContentValues(s);
        mSettingsDb.insert(SettingsDbSchema.SettingsTable.SET, null, values);
    }

    public File getPhotoFile(Trip trip) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, trip.getPhotoFilename());
    }

    public void updateTrip(Trip trip) {
        String uuidString = trip.getId().toString();
        ContentValues values = getContentValues(trip);
        mDatabase.update(TripDbSchema.TripTable.NAME, values,
                TripDbSchema.TripTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public void updateSettings(Settings settings) {
        String uuidString = settings.getSId().toString();
        ContentValues values = getSContentValues(settings);
        mSettingsDb.update(SettingsDbSchema.SettingsTable.SET, values,
                SettingsDbSchema.SettingsTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }
    private static ContentValues getContentValues(Trip trip) {
        ContentValues values = new ContentValues();
        values.put(TripDbSchema.TripTable.Cols.UUID, trip.getId().toString());
        values.put(TripDbSchema.TripTable.Cols.TITLE, trip.getTitle());
        values.put(TripDbSchema.TripTable.Cols.DATE, trip.getDate().getTime());
        values.put(TripDbSchema.TripTable.Cols.DESTINATION,trip.getDestination());
        values.put(TripDbSchema.TripTable.Cols.COMMENT,trip.getComment());
        values.put(TripDbSchema.TripTable.Cols.DURATION, trip.getDuration());
        values.put(TripDbSchema.TripTable.Cols.TRIP_TYPE, trip.getTripType());
        return values;
    }
    private static ContentValues getSContentValues(Settings settings) {
        ContentValues values = new ContentValues();
        values.put(SettingsDbSchema.SettingsTable.Cols.UUID, settings.getSId().toString());
        values.put(SettingsDbSchema.SettingsTable.Cols.NAME, settings.getName());
        values.put(SettingsDbSchema.SettingsTable.Cols.ID, settings.getPersonId());
        values.put(SettingsDbSchema.SettingsTable.Cols.EMAIL,settings.getEmail());
        values.put(SettingsDbSchema.SettingsTable.Cols.GENDER, settings.getGender());
        values.put(SettingsDbSchema.SettingsTable.Cols.COMMENT, settings.getComment());
        return values;
    }

    private TripCursorWrapper queryTrips(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TripDbSchema.TripTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new TripCursorWrapper(cursor);
    }
    private SettingsCursorWrapper querySettings(String whereClause, String[] whereArgs) {
        Cursor cursor = mSettingsDb.query(
                SettingsDbSchema.SettingsTable.SET,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new SettingsCursorWrapper(cursor);

    }


//public void deleteTrip(Trip trip){
//
//    mDatabase.delete(TripDbSchema.TripTable.NAME, TripDbSchema.TripTable.Cols.UUID, new String[]{TripDbSchema.TripTable.Cols.UUID});
//}

}
