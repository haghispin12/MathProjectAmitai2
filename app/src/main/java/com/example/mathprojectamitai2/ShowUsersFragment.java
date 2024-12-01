package com.example.mathprojectamitai2;

import static android.app.Activity.RESULT_OK;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
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
    private ImageView ivProfileImage;
    private Button btAddUser;
    Uri uri;
    private Button btBackMain;

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        ivProfileImage.setImageURI(uri);
                    }
                }
            });

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
        ivProfileImage = view.findViewById(R.id.ivProfilImage);
        btAddUser = view.findViewById(R.id.btAddUser);
        btBackMain = view.findViewById(R.id.btBackMain);

        btAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startCamera.launch(cameraIntent);
            }
        });
        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        tvRating.setText("Rate:" + mainViewModel.user.getRate()+"");
        etFragmentUserName.setText(mainViewModel.user.getName());
        tvScore.setText("score:" + mainViewModel.user.getScore());

        btBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });
    }
}