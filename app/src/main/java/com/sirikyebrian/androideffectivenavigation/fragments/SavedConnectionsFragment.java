package com.sirikyebrian.androideffectivenavigation.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.PreferenceKeys;
import com.sirikyebrian.androideffectivenavigation.Utils.Users;
import com.sirikyebrian.androideffectivenavigation.adapters.UserListAdapter;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedConnectionsFragment extends Fragment {

    private static final String TAG = SavedConnectionsFragment.class.getSimpleName();

    public static final int NUM_GRID_COLUMNS = 2;

    private UserListAdapter mUserListAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<User> mUserList = new ArrayList<>();


    public SavedConnectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_connections, container, false);
        Log.d(TAG, "onCreateView: started");

        mRecyclerView = view.findViewById(R.id.connectionsRecyclerView);

        getConnections();
        return view;

    }

    private void getConnections() {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        Set<String> savedNames = preferences.getStringSet(PreferenceKeys.SAVED_CONNECTIONS,
                new HashSet<String>());
        Users users = new Users();
        if (mUserList != null) {
            mUserList.clear();
        }
        for (User user : users.USERS) {
            if (savedNames.contains(user.getName())) {
                mUserList.add(user);
            }
        }
        if (mUserListAdapter == null) {
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        mUserListAdapter = new UserListAdapter(getActivity(), mUserList);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(NUM_GRID_COLUMNS, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mUserListAdapter);
    }

}
