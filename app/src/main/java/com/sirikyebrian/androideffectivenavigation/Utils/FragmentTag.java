package com.sirikyebrian.androideffectivenavigation.Utils;

import android.support.v4.app.Fragment;

/**
 * Created by Sirikye Brian on 8/13/2019.
 * bryanmuloni@gmail.com
 */
public class FragmentTag {
    private Fragment fragment;
    private String tag;

    public FragmentTag(Fragment fragment, String tag) {
        this.fragment = fragment;
        this.tag = tag;
    }

    public FragmentTag() {
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

