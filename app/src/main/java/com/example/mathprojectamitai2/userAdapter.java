package com.example.mathprojectamitai2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {


    public interface OnItemClickListener{
        void onItemClick(Fruit item);
    }

    private ArrayList<Fruit>fruits;
    private OnItemClickListener listener;

    public userAdapter(ArrayList<Fruit> fruits, OnItemClickListener listener) {
        this.fruits = fruits;
        this.listener = listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(User.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvFruitName;
        ImageView ivFruitImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.tvFruitName);
            ivFruitImg = itemView.findViewById(R.id.ivfruitlmg);
        }

        public void bind(final Fruit item, final OnItemClickListener listener){
            tvFruitName.setText(item.getName());
            ivFruitImg.setImageResource(item.getDrawable());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

    }



}