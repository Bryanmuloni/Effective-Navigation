package com.sirikyebrian.androideffectivenavigation.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.PreferenceKeys;
import com.sirikyebrian.androideffectivenavigation.Utils.Users;
import com.sirikyebrian.androideffectivenavigation.adapters.MessageListAdapter;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {

    private static final String TAG = MessagesFragment.class.getSimpleName();


    private MessageListAdapter mMessageListAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<User> mUserList = new ArrayList<>();

    private SearchView mSearchView;

    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messages, container, false);
        mRecyclerView = view.findViewById(R.id.messagesRecyclerView);
        mSearchView = view.findViewById(R.id.search_view);

        getConnections();
        initSearchView();
        return view;
    }

    private void initSearchView() {
// Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        mSearchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mMessageListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mMessageListAdapter.getFilter().filter(query);
                return false;
            }
        });
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
        if (mMessageListAdapter == null) {
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        mMessageListAdapter = new MessageListAdapter(getActivity(), mUserList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mMessageListAdapter);

    }

}
