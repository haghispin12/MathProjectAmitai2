package com.example.mathprojectamitai2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowUsersFragment extends Fragment {
    MainViewModel mainViewModel;
    private EditText etFragmentUserName;
    private TextView tvScore;
    private TextView tvRating;
    private Button btAddPicture;
    private ImageView ivProfilImage;
    private Button btAddUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_show_users, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        initview(view);
        return view;
    }

    public void initview(View view) {
        etFragmentUserName = view.findViewById(R.id.etFragmentUserName);
        tvScore = view.findViewById(R.id.tvScore);
        tvRating = view.findViewById(R.id.tvRating);
        btAddPicture = view.findViewById(R.id.btAddPicture);
        ivProfilImage = view.findViewById(R.id.ivProfilImage);
        btAddUser = view.findViewById(R.id.btAddUser);

        btAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvRating.setText(mainViewModel.user.getRate()+"");
        etFragmentUserName.setText(mainViewModel.user.getName());
    }
}