package com.bignerdranch.android.triplogger;

import android.widget.Spinner;

import java.util.Date;
import java.util.UUID;


public class Trip {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mDestination;
    private String mComment;
    private String mDuration;
    private String mTripType;


    public Trip() {
        this(UUID.randomUUID());
    }
    public Trip(UUID id) {
        mId = id;
        mDate = new Date();
    }
    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }
    public void setDate(Date date) {
        mDate = date;
    }

    public String getDestination() {
        return mDestination;
    }
    public void setDestination(String destination) {
        mDestination = destination;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
    public String getComment(){
        return mComment;
    }
    public void setComment(String comment){
        mComment = comment;
    }
    public String getDuration(){
        return mDuration;
    }
    public void setDuration(String duration){
        mDuration = duration;
    }
    public String getTripType(){return mTripType; }
    public void setTripType(String triptype){mTripType = triptype;}

}
