package com.example.volleyintroduction2.Adapter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.volleyintroduction2.Models.User;
import com.example.volleyintroduction2.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    //User adapter extends to Base adapter which is abstract class
    //So we can make Useradapter abstract or can define all the methods of BaseAdapter in userAdapter
    private Context mContext;
    private List<User> mUsers;
    String TAG = "UsersAdapter";

    public UserAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
        Log.d(TAG, "UserAdapter: " + users.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_user, parent, false);
        //1) When attachToRoot = false it means. Dont attach the child view to parent "Right Now", Add it later.
        // 2) When attachToRoot = true it means.
        // Attach the childView to parent "Right Now". In both cases the child view will be added to parentView eventually.
        TextView name, email, home, mobile;
        name = convertView.findViewById(R.id.textUser);
        email = convertView.findViewById(R.id.textEmail);
        home = convertView.findViewById(R.id.textHome);
        mobile = convertView.findViewById(R.id.textMobile);

        name.setText(mUsers.get(position).getName());
        email.setText(mUsers.get(position).getEmail());
        home.setText(mUsers.get(position).getPhone().getHome());
        mobile.setText(mUsers.get(position).getPhone().getMobile());
        return convertView;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
