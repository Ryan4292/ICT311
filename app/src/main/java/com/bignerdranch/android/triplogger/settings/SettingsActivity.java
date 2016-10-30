package com.bignerdranch.android.triplogger.settings;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.bignerdranch.android.triplogger.SingleFragmentActivity;
import com.bignerdranch.android.triplogger.TripFragment;

import java.util.UUID;


public class SettingsActivity extends SingleFragmentActivity {
    private static final String EXTRA_SETTINGS_ID =
            "com.bignerdranch.android.triplogger.settings_id";

    public static Intent newIntent(Context packageContext, UUID settingsId){
        Intent intent = new Intent(packageContext,SettingsActivity.class);
        intent.putExtra(EXTRA_SETTINGS_ID, settingsId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID settingsId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_SETTINGS_ID);
        return SettingsFragment.newInstance(settingsId);
    }
}
