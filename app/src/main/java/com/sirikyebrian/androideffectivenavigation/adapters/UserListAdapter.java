package com.sirikyebrian.androideffectivenavigation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.IMainActivity;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.ArrayList;

/**
 * Created by Sirikye John on 8/7/2019.
 * bryanmuloni@gmail.com
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private static final String TAG = "UserListAdapter";
    private ArrayList<User> mUsers = new ArrayList<>();
    private Context mContext;
    private IMainActivity iMainActivity;

    public UserListAdapter(Context context, ArrayList<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_main_feed
                , viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        RequestOptions requestOptions =
                new RequestOptions().placeholder(R.drawable.dating);

        Glide.with(mContext).load(mUsers.get(position).getProfile_image())
                .apply(requestOptions)
                .into(userViewHolder.image);
        userViewHolder.name.setText(mUsers.get(position).getName());
        userViewHolder.interested_in.setText(mUsers.get(position).getInterested_in());
        userViewHolder.status.setText(mUsers.get(position).getStatus());

        userViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: card clicked on " + mUsers.get(position).getName());
//                Toast.makeText(mContext, "Clicked on: "+mUsers.get(position).getName(),
//                        Toast.LENGTH_SHORT).show();
                iMainActivity.inflateViewProfileFragment(mUsers.get(position));

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        iMainActivity = (IMainActivity) mContext;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView interested_in;
        TextView status;
        CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            interested_in = itemView.findViewById(R.id.interested_in);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
