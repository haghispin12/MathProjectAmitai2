package com.example.mathprojectamitai2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(User item);
    }

    private ArrayList<User>users;
    private OnItemClickListener listener;

    public UserAdapter(ArrayList<User> users, OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(users.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserName;
        TextView tvUserRate;
        ImageView ivUserPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserRate = itemView.findViewById(R.id.tvUserRate);
            ivUserPic = itemView.findViewById(R.id.ivUserPic);
        }

        public void bind(final User item, final OnItemClickListener listener){
            tvUserName.setText(item.getName());
            tvUserRate.setText(item.getRate()+"");
            ivUserPic.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

    }



}