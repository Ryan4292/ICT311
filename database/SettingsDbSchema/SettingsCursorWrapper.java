package com.bignerdranch.android.triplogger.database.SettingsDbSchema;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.triplogger.Trip;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripDbSchema;
import com.bignerdranch.android.triplogger.settings.Settings;

import java.util.Date;
import java.util.UUID;


public class SettingsCursorWrapper extends CursorWrapper {
    public SettingsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Settings getSettings() {

        String uuid = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.UUID));
        String name = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.NAME));
        String id = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.ID));
        String email = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.EMAIL));
        String gender = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.GENDER));
        String comment = getString(getColumnIndex(SettingsDbSchema.SettingsTable.Cols.COMMENT));

        Settings settings = new Settings(UUID.fromString(uuid));
        settings.setName(name);
        settings.setPersonId(id);
        settings.setEmail(email);
        settings.setGender(gender);
        settings.setComment(comment);
        return settings;
    }
}
