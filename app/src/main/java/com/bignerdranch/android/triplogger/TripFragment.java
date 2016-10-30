package com.bignerdranch.android.triplogger;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bignerdranch.android.triplogger.database.TripDbSchema.TripBaseHelper;
import com.bignerdranch.android.triplogger.database.TripDbSchema.TripDbSchema;

import java.io.File;
import java.util.List;
import java.util.UUID;


public class TripFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String ARG_TRIP_ID = "trip_id";
    private static final int REQUEST_PHOTO= 2;
    private Trip mTrip;
    private EditText mTitleField;
    private Button mDateButton;
    private EditText mDestinationField;
    private EditText mCommentField;
    private EditText mDurationField;
    private Button mSave;
    private Button mDelete;
    private Button mCancel;
    private Spinner mSpinner;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;
    private Context mApplicationContext;

    public static TripFragment newInstance(UUID tripId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_ID, tripId);

        TripFragment fragment = new TripFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID tripId = (UUID) getArguments().getSerializable(ARG_TRIP_ID);
        mTrip = TripLab.get(getActivity()).getTrip(tripId);
        mPhotoFile = TripLab.get(getActivity()).getPhotoFile(mTrip);
    }


    @Override
    public void onPause() {
        super.onPause();
        TripLab.get(getActivity())
                .updateTrip(mTrip);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);
        mTitleField = (EditText)v.findViewById(R.id.trip_title);
        mTitleField.setText(mTrip.getTitle());


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mTrip.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
// This one too
            }
        });

        mDateButton = (Button)v.findViewById(R.id.trip_date);
        mDateButton.setText(mTrip.getDate().toString());
        mDateButton.setEnabled(false);

        mCancel = (Button) v.findViewById(R.id.cancel);

        mDelete = (Button) v.findViewById(R.id.delete);

        mDelete.setEnabled(true);


        mSave = (Button) v.findViewById(R.id.save);

        mSpinner  = (Spinner) v.findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);


        mDestinationField = (EditText)v.findViewById(R.id.trip_destination);
        mDestinationField.setText(mTrip.getDestination());
        mDestinationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence d, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence d, int start, int before, int count) {
                mTrip.setDestination(d.toString());
            }
            @Override
            public void afterTextChanged(Editable d) {
// This one too
            }
        });
        mCommentField = (EditText)v.findViewById(R.id.comment);
        mCommentField.setText(mTrip.getComment());
        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence d, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence d, int start, int before, int count) {
                mTrip.setComment(d.toString());
            }
            @Override
            public void afterTextChanged(Editable d) {
// This one too
            }
        });
        mDurationField = (EditText)v.findViewById(R.id.duration);
        mDurationField.setText(mTrip.getDuration());
        mDurationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence d, int start, int count, int after) {
// This space intentionally left blank
            }
            @Override
            public void onTextChanged(
                    CharSequence d, int start, int before, int count) {
                mTrip.setDuration(d.toString());
            }
            @Override
            public void afterTextChanged(Editable d) {
// This one too
            }
        });
        PackageManager packageManager = getActivity().getPackageManager();


        mPhotoButton = (ImageButton) v.findViewById(R.id.trip_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);
        if (canTakePhoto) {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        mPhotoView = (ImageView) v.findViewById(R.id.trip_photo);
        updatePhotoView();

        mSpinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.trip_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);


        return v;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        }


    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
