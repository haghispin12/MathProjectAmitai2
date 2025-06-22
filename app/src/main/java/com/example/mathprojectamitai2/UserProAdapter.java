package com.example.mathprojectamitai2;

// UserAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojectamitai2.MathPro.User;
import com.example.mathprojectamitai2.MathPro.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserProAdapter extends RecyclerView.Adapter<UserProAdapter.UserViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(User_pro item);
    }
    private List<User_pro> userList;
    private OnItemClickListener listener;


    public UserProAdapter(List<User_pro> userList, OnItemClickListener listener) {
        this.userList = userList;
        this.listener = listener;
    }



    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // אינפלציה של ה-XML לפריט הרשימה
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pro_user, parent, false);
        return new UserViewHolder(view);
    }

    // אתחול ה-Adapter עם הרשימה
    public UserProAdapter(List<User_pro> userList) {
        this.userList = userList;
    }





    // ViewHolder שמכיל את כל ה-Views שלנו
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProUserName;
        public TextView tvProUserScore;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvProUserName = itemView.findViewById(R.id.tvProUserName);
            tvProUserScore = itemView.findViewById(R.id.tvProUserScore);
        }


        public void bind (final User_pro item, final UserAdapter.OnItemClickListener listener){
            tvProUserName.setText(item.getUID());
            tvProUserScore.setText(item.getScore()+"");
        }

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User_pro currentUser = userList.get(position);

        // חילוץ שם המשתמש לפני ה-@
        String name = currentUser.getUID();
        String username = name;
        int atIndex = name.indexOf('@');
        if (atIndex != -1) {
            username = name.substring(0, atIndex); // חותכים את מה שיש לפני ה-@
        }

        // עדכון ה-TextView עם שם המשתמש
        holder.tvProUserName.setText(username);

        // הצגת הציון
        holder.tvProUserScore.setText("Score: " + currentUser.getScore());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

