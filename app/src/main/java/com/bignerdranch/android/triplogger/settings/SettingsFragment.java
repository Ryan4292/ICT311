package com.bignerdranch.android.triplogger.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bignerdranch.android.triplogger.R;
import com.bignerdranch.android.triplogger.Trip;
import com.bignerdranch.android.triplogger.TripLab;

import java.util.UUID;


public class SettingsFragment extends Fragment {
    private static final String ARG_SETTINGS_ID = "setting_id";
    private Settings mSettings;
    private EditText mNameField;
    private EditText mEmailField;
    private EditText mIdField;
    private EditText mGenderField;
    private EditText mCommentField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = new Settings();
        UUID settingId = (UUID) getArguments().getSerializable(ARG_SETTINGS_ID);
        mSettings = TripLab.get(getActivity()).getSettings(settingId);
    }
    public static SettingsFragment newInstance(UUID settingId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_SETTINGS_ID, settingId);

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onPause() {
        super.onPause();
        TripLab.get(getActivity())
                .updateSettings(mSettings);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_fragment, container, false);

        mNameField = (EditText)v.findViewById(R.id.person_name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });
        mIdField = (EditText)v.findViewById(R.id.person_id);
        mIdField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setPersonId(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        mEmailField = (EditText)v.findViewById(R.id.email_address);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setEmail(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        mGenderField = (EditText)v.findViewById(R.id.gender);
        mGenderField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setGender(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });
        mCommentField= (EditText)v.findViewById(R.id.comment);
        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSettings.setComment(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        return v;
    }

}
