package com.sirikyebrian.androideffectivenavigation.Utils;

import com.sirikyebrian.androideffectivenavigation.models.Message;
import com.sirikyebrian.androideffectivenavigation.models.User;

/**
 * Created by Sirikye Brian on 8/7/2019.
 * bryanmuloni@gmail.com
 */
public interface IMainActivity {
    void inflateViewProfileFragment(User user);

    void onMessageSelected(Message message);

    void onBackPressed();
}
