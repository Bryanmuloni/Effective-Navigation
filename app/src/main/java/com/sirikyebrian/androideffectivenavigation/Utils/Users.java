package com.sirikyebrian.androideffectivenavigation.Utils;

import android.net.Uri;

import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.models.User;

/**
 * Created by Sirikye John on 8/7/2019.
 * bryanmuloni@gmail.com
 */
public class Users {

    private static String getProfileImage(int drawable) {
        String image =
                Uri.parse("android.resource://com.sirikyebrian.androideffectivenavigation/" + drawable).toString();
        return image;
    }

    public User[] USERS = {John, Mark,Ann,Christopher,Travis, Mildred, James, Henry, Elizabeth,
            Eunice,
            Jessica,
            Mary};

    public static final User John = new User(getProfileImage(R.drawable.john), "John", "Male",
            "Female",
            "Looking");
    public static final User Mark = new User(getProfileImage(R.drawable.mark), "Mark", "Male",
            "Female", "Looking");
    public static final User Ann = new User(getProfileImage(R.drawable.ann), "Ann", "Female",
            "Male", "Looking");
    public static final User Christopher = new User(getProfileImage(R.drawable.christopher), "Christopher", "Male",
            "Female", "Looking");
    public static final User Travis = new User(getProfileImage(R.drawable.gab), "Travis", "Male",
            "Female", "Looking");
    public static final User Mildred = new User(getProfileImage(R.drawable.jenel), "Mildred", "Female",
            "Male", "Looking");
    public static final User James = new User(getProfileImage(R.drawable.james), "James", "Male",
            "Female", "Looking");
    public static final User Henry = new User(getProfileImage(R.drawable.mc), "Henry", "Male",
            "Female", "Looking");
    public static final User Elizabeth = new User(getProfileImage(R.drawable.line), "Elizabeth",
            "Female",
            "Male", "Looking");
    public static final User Eunice = new User(getProfileImage(R.drawable.lynn), "Eunice", "Female",
            "Male", "Looking");
    public static final User Jessica = new User(getProfileImage(R.drawable.rosilla), "Jessica", "Female",
            "Male", "Looking");
    public static final User Mary = new User(getProfileImage(R.drawable.zoey), "Mary", "Male",
            "Male", "Looking");
}
