package com.bignerdranch.android.triplogger.database.TripDbSchema;


public class TripDbSchema {


    public static final class TripTable{
        public static final String NAME = " trip";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String DESTINATION = "destination";
            public static final String COMMENT = "comment";
            public static final String DURATION = "duration";
            public static final String TRIP_TYPE = "trip_type";
        }
    }


}
