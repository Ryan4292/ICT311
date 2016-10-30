package com.bignerdranch.android.triplogger.database.TripDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.triplogger.Trip;

import java.util.Date;
import java.util.UUID;


public class TripCursorWrapper extends CursorWrapper {
    public TripCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Trip getTrip() {
        String uuidString = getString(getColumnIndex(TripDbSchema.TripTable.Cols.UUID));
        String title = getString(getColumnIndex(TripDbSchema.TripTable.Cols.TITLE));
        long date = getLong(getColumnIndex(TripDbSchema.TripTable.Cols.DATE));
        String destination = getString(getColumnIndex(TripDbSchema.TripTable.Cols.DESTINATION));
        String duration = getString(getColumnIndex(TripDbSchema.TripTable.Cols.DURATION));
        String triptype = getString(getColumnIndex(TripDbSchema.TripTable.Cols.TRIP_TYPE));
        String comment = getString(getColumnIndex(TripDbSchema.TripTable.Cols.COMMENT));


        Trip trip = new Trip(UUID.fromString(uuidString));
        trip.setTitle(title);
        trip.setDate(new Date(date));
        trip.setDestination(destination);
        trip.setDuration(duration);
        trip.setTripType(triptype);
        trip.setComment(comment);

        return trip;
    }
}
