package com.bignerdranch.android.triplogger.settings;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;


public class Settings {
        private UUID mSId;
        private String mName;
        private String mEmail;
        private String mGender;
        private String mComment;
        private String mPersonId;


        public Settings() {
            mSId = (UUID.randomUUID());
        }
        public Settings(UUID id) {
            mSId = id;

    }
        public UUID getSId() {
            return mSId;
        }
        public String getName() {
            return mName;
        }
        public void setName(String name) {
            mName = name;
        }
        public String getEmail() {
            return mName;
        }
        public void setEmail(String email) {
            mEmail = email;
        }

        public String getGender() {
            return mName;
        }
        public void setGender(String gender) {
            mGender = gender;
        }

        public String getPersonId() {
            return mName;
        }
        public void setPersonId(String personId) {
            mPersonId = personId;
        }

        public String getComment() {
            return mName;
        }
        public void setComment(String comment) {
            mComment = comment;
        }}




