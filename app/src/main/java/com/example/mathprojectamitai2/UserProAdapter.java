package com.example.mathprojectamitai2;

// UserAdapter.java

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserProAdapter extends RecyclerView.Adapter<UserProAdapter.UserViewHolder> {
    private List<User_pro> userList;

    // ViewHolder שמכיל את כל ה-Views שלנו
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProUserName;
        public TextView tvProUserScore;

        public UserViewHolder(View itemView) {
            super(itemView);
            tvProUserName = itemView.findViewById(R.id.tvProUserName);
            tvProUserScore = itemView.findViewById(R.id.tvProUserScore);
        }
    }

    // אתחול ה-Adapter עם הרשימה
    public UserProAdapter(List<User_pro> userList) {
        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // אינפלציה של ה-XML לפריט הרשימה
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
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

