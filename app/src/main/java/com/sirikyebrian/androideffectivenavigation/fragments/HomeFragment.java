package com.sirikyebrian.androideffectivenavigation.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.Users;
import com.sirikyebrian.androideffectivenavigation.adapters.UserListAdapter;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private static final int NUM_COLUMNS = 2;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private UserListAdapter userListAdapter;
    private RecyclerView recyclerView;

    private StaggeredGridLayoutManager staggeredGridLayoutManager;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "onCreateView: started");
        recyclerView = rootView.findViewById(R.id.userListRecyclerView);

        findMatches();

        return rootView;
    }

    private void findMatches() {
        Users users = new Users();
        if (users != null) {
            userArrayList.clear();
        }
        for (User user : users.USERS) {
            userArrayList.add(user);
        }
        if (userListAdapter == null) {
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init RecyclerView");
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS,
                LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        userListAdapter = new UserListAdapter(getActivity(), userArrayList);
        recyclerView.setAdapter(userListAdapter);
    }

}
