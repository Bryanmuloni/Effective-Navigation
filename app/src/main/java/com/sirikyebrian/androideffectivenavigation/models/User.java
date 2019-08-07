package com.sirikyebrian.androideffectivenavigation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sirikye John on 8/7/2019.
 * bryanmuloni@gmail.com
 */
public class User implements Parcelable {

    private String profile_image;
    private String name;
    private String gender;
    private String interested_in;
    private String status;


    public User(){

    }
    public User(String profile_image, String name, String gender, String interested_in, String status) {
        this.profile_image = profile_image;
        this.name = name;
        this.gender = gender;
        this.interested_in = interested_in;
        this.status = status;
    }

    protected User(Parcel parcel) {
        profile_image = parcel.readString();
        name = parcel.readString();
        gender = parcel.readString();
        interested_in = parcel.readString();
        status = parcel.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(profile_image);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(interested_in);
        dest.writeString(status);
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterested_in() {
        return interested_in;
    }

    public void setInterested_in(String interested_in) {
        this.interested_in = interested_in;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
