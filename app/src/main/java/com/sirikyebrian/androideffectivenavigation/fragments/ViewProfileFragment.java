package com.sirikyebrian.androideffectivenavigation.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment {
    private static final String TAG = "ViewProfileFragment";

    private TextView nameText;
    private TextView genderText;
    private TextView interestText;
    private TextView statusText;
    private ImageView profileImage;

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


        nameText.setText(mUser.getName());
        genderText.setText(mUser.getGender());
        interestText.setText(mUser.getInterested_in());
        statusText.setText(mUser.getStatus());

        RequestOptions requestOptions =
                new RequestOptions().placeholder(R.drawable.dating);

        Glide.with(this).load(mUser.getProfile_image())
                .apply(requestOptions)
                .into(profileImage);
        return view;
    }

}
