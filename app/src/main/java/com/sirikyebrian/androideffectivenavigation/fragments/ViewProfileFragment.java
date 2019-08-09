package com.sirikyebrian.androideffectivenavigation.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.PreferenceKeys;
import com.sirikyebrian.androideffectivenavigation.Utils.Resources;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment implements OnLikeListener {
    private static final String TAG = "ViewProfileFragment";

    private TextView nameText;
    private TextView genderText;
    private TextView interestText;
    private TextView statusText;
    private ImageView profileImage;
    private LikeButton mLikeButton;

    private User mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mUser = bundle.getParcelable(getString(R.string.intent_user));
            Log.d(TAG, "onCreate: got incoming bundle: " + mUser.getName());
        }
    }

    public ViewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_profile, container, false);
        nameText = view.findViewById(R.id.nameDetaIl);
        genderText = view.findViewById(R.id.genderDetail);
        interestText = view.findViewById(R.id.interestedInDetail);
        statusText = view.findViewById(R.id.statusDetail);
        profileImage = view.findViewById(R.id.imageDetail);
        mLikeButton = view.findViewById(R.id.likeButton);
        mLikeButton.setOnLikeListener(this);

        checkIfConnected();
        setBackgroundImage(view);
        init();
        return view;
    }

    private void init() {
        Log.d(TAG, "init: initializing ViewProfileFragment.");

        if (mUser != null) {
            Glide.with(getActivity()).load(mUser.getProfile_image())
                    .into(profileImage);
            nameText.setText(mUser.getName());
            genderText.setText(mUser.getGender());
            interestText.setText(mUser.getInterested_in());
            statusText.setText(mUser.getStatus());
        }
    }

    private void checkIfConnected() {
        Log.d(TAG, "liked: liked.");
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS,
                new HashSet<String>());
        savedNames.add(mUser.getName());

        if (savedNames.contains(mUser.getName())) {
            mLikeButton.setLiked(true);
        } else {
            mLikeButton.setLiked(false);
        }
    }

    private void setBackgroundImage(View view) {
        ImageView backgroundImage = view.findViewById(R.id.background);
        Glide.with(getActivity()).load(Resources.BACKGROUND_HEARTS)
                .into(backgroundImage);
    }

    @Override
    public void liked(LikeButton likeButton) {
        Log.d(TAG, "liked: liked.");
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS,
                new HashSet<String>());
        savedNames.add(mUser.getName());
        editor.putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames);
        editor.apply();
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        Log.d(TAG, "unLiked: unliked.");
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS,
                new HashSet<String>());
        savedNames.remove(mUser.getName());

        editor.remove(PreferenceKeys.SAVED_CONNECTIONS);
        editor.apply();


        editor.putStringSet(PreferenceKeys.SAVED_CONNECTIONS, savedNames);
        editor.apply();
    }
}
