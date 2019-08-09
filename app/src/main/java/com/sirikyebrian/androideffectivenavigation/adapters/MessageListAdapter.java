package com.sirikyebrian.androideffectivenavigation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sirikyebrian.androideffectivenavigation.R;
import com.sirikyebrian.androideffectivenavigation.Utils.IMainActivity;
import com.sirikyebrian.androideffectivenavigation.Utils.Messages;
import com.sirikyebrian.androideffectivenavigation.models.Message;
import com.sirikyebrian.androideffectivenavigation.models.User;

import java.util.ArrayList;

/**
 * Created by Sirikye Brian on 8/8/2019.
 * bryanmuloni@gmail.com
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> implements Filterable {

    private static final String TAG = "MessageListAdapter";

    private ArrayList<User> mUsers = new ArrayList<>();
    private ArrayList<User> mFilteredUsers = new ArrayList<>();

    private Context mContext;
    private IMainActivity mInterface;

    public MessageListAdapter(Context context, ArrayList<User> arrayList) {
        mContext = context;
        mUsers = arrayList;
        mFilteredUsers = arrayList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_row,
                parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        final User user = mFilteredUsers.get(position);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.dating);

        Glide.with(mContext)
                .load(user.getProfile_image())
                .apply(requestOptions)
                .into(messageViewHolder.image);

        messageViewHolder.name.setText(user.getName());
        messageViewHolder.message.setText(Messages.MESSAGES[position]); // generate a random message

        messageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + user.getName());

                mInterface.onMessageSelected(new Message(mUsers.get(position),
                        Messages.MESSAGES[position]));
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mInterface = (IMainActivity) mContext;
    }

    @Override
    public int getItemCount() {
        return mFilteredUsers.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mFilteredUsers = mUsers;
                } else {
                    ArrayList<User> filteredList = new ArrayList<>();
                    for (User row : mUsers) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mFilteredUsers = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredUsers;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredUsers = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView reply;
        TextView message;
        ImageView image;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.profileName);
            reply = itemView.findViewById(R.id.replyText);
            message = itemView.findViewById(R.id.messageText);
            image = itemView.findViewById(R.id.profilePhoto);
        }
    }
}
