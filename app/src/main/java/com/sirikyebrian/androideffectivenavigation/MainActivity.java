package com.sirikyebrian.androideffectivenavigation;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.sirikyebrian.androideffectivenavigation.Utils.IMainActivity;
import com.sirikyebrian.androideffectivenavigation.Utils.PreferenceKeys;
import com.sirikyebrian.androideffectivenavigation.fragments.AgreementFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.ChatFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.HomeFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.MessagesFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.SavedConnectionsFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.SettingsFragment;
import com.sirikyebrian.androideffectivenavigation.fragments.ViewProfileFragment;
import com.sirikyebrian.androideffectivenavigation.models.Message;
import com.sirikyebrian.androideffectivenavigation.models.User;

public class MainActivity extends AppCompatActivity implements IMainActivity,
        BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    private BottomNavigationViewEx mBottomNavigationViewEx;
    private ImageView mHeaderImage;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationViewEx = findViewById(R.id.bottom_nav_view);
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(this);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        mHeaderImage = headerView.findViewById(R.id.header_image);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        isFirstLogin();
        initBottomNavigationView();
        setNavigationViewListener();
        setHeaderImage();
        init();
    }

    private void initBottomNavigationView() {
        Log.d(TAG, "initBottomNavigationView: initializing bottom navigation view");
        mBottomNavigationViewEx.enableAnimation(false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home: {
                init();
                break;
            }
            case R.id.navigation_settings: {
                Log.d(TAG, "onNavigationItemSelected: SettingsFragment");
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, settingsFragment,
                        getString(R.string.tag_fragment_settings));
                transaction.addToBackStack(getString(R.string.tag_fragment_settings));
                transaction.commit();
                break;
            }
            case R.id.navigation_agreement: {
                Log.d(TAG, "onNavigationItemSelected: AgreementFragment");
                AgreementFragment agreementFragment = new AgreementFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, agreementFragment,
                        getString(R.string.tag_fragment_agreement));
                transaction.addToBackStack(getString(R.string.tag_fragment_agreement));
                transaction.commit();
                break;
            }
            case R.id.nav_home:{
                Log.d(TAG, "onNavigationItemSelected: HomeFragment");
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, homeFragment,
                        getString(R.string.tag_fragment_home));
                transaction.addToBackStack(getString(R.string.tag_fragment_home));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
            case R.id.nav_connections:{
                Log.d(TAG, "onNavigationItemSelected: ConnectionsFragment");
                SavedConnectionsFragment savedConnectionsFragment = new SavedConnectionsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, savedConnectionsFragment,
                        getString(R.string.tag_fragment_saved_connections));
                transaction.addToBackStack(getString(R.string.tag_fragment_saved_connections));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
            case R.id.nav_messages:{
                Log.d(TAG, "onNavigationItemSelected: MessagesFragment");
                MessagesFragment messagesFragment = new MessagesFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content_frame, messagesFragment,
                        getString(R.string.tag_fragment_messages));
                transaction.addToBackStack(getString(R.string.tag_fragment_messages));
                transaction.commit();
                menuItem.setChecked(true);
                break;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void init() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, homeFragment,
                getString(R.string.tag_fragment_home));
        transaction.addToBackStack(getString(R.string.tag_fragment_home));
        transaction.commit();
    }

    private void setNavigationViewListener(){
        Log.d(TAG, "setNavigationViewListener: initializing navigation drawer listener");
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setHeaderImage() {
        Log.d(TAG, "setHeaderImage: setting header image for navigation drawer");
        Glide.with(this)
                .load(R.drawable.couple)
                .into(mHeaderImage);
    }

    private void isFirstLogin() {
        Log.d(TAG, "isFirstLogin: checking if this is first login");
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstTimeLogin = preferences.getBoolean(PreferenceKeys.FIRST_TIME_LOGIN, true);
        if (isFirstTimeLogin) {
            Log.d(TAG, "isFirstLogin: launching alert dialog");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.first_time_user_message));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "onClick: closing the dialog");

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(PreferenceKeys.FIRST_TIME_LOGIN, false);
                    editor.apply();
                    dialog.dismiss();
                }
            });
            builder.setIcon(R.drawable.heart_on);
            builder.setTitle("");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void inflateViewProfileFragment(User user) {
        ViewProfileFragment profileFragment = new ViewProfileFragment();

        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_user),user);
        profileFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, profileFragment,
                getString(R.string.tag_fragment_view_profile));
        transaction.addToBackStack(getString(R.string.tag_fragment_view_profile));
        transaction.commit();
    }

    @Override
    public void onMessageSelected(Message message) {
        ChatFragment chatFragment = new ChatFragment();

        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.intent_message),message);
        chatFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content_frame, chatFragment,
                getString(R.string.tag_fragment_chat));
        transaction.addToBackStack(getString(R.string.tag_fragment_chat));
        transaction.commit();
    }


}
