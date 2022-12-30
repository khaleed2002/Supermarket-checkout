package com.example.supermarket_checkout;

public class Model {

    String mName;
    String mDetails;
    int mPhoto;

    public Model(String mName, String mDetails, int mPhoto) {
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhoto = mPhoto;
    }




    public String getmName() {
        return mName;
    }

    public String getmDetail() {
        return mDetails;
    }

    public int getmPhoto() {
        return mPhoto;
    }

}
