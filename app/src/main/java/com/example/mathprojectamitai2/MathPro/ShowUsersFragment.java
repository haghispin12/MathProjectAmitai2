package com.example.mathprojectamitai2.MathPro;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathprojectamitai2.R;

import java.util.ArrayList;


public class ShowUsersFragment extends Fragment implements MenuProvider {
    MainViewModel mainViewModel;
    private EditText etFragmentUserName;
    private TextView tvScore;
    private TextView tvRating;
    private Button btAddPicture;
    private ImageView ivProfileImage;
    private Button btAddUser;
    Uri uri;
    private Button btBackMain;
    private RecyclerView rcShowUsers;
    private MenuItem itemDelete;
    private MenuItem itemEdit;




    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {  //apdate user
                    if (result.getResultCode() == RESULT_OK) {
                        ivProfileImage.setImageURI(uri);
                        mainViewModel.user.setUri(uri);
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
        View view=inflater.inflate(R.layout.fragment_show_users, container, false);  //creating view
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initview(view);
        mainViewModel.users.observe(requireActivity(), new Observer<ArrayList<User>>() {      //listening and apdate changes in users
            @Override
            public void onChanged(ArrayList<User> users) {
                int t =10;
                mainViewModel.users.observe(requireActivity(), new Observer<ArrayList<User>>() {
                    @Override
                    public void onChanged(ArrayList<User> users) {
                        UserAdapter fa = new UserAdapter(users, new UserAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(User item) {
                                int n=10;
                                itemDelete.setVisible(true);
                                itemEdit.setVisible(true);
                                mainViewModel.currentUser = item;
                                setValues(mainViewModel.currentUser);

                            }

                        });

                        rcShowUsers.setLayoutManager(new LinearLayoutManager(requireActivity()));
                        rcShowUsers.setAdapter(fa);
                        rcShowUsers.setHasFixedSize(true);


                    }
                });

            }



    });
        requireActivity().addMenuProvider(this);
        mainViewModel.dbSellectAll(getActivity());
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
        rcShowUsers = view.findViewById(R.id.rcShowUsers);

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
            if (getActivity()!=null) {
                long id = mainViewModel.dbAddUser(getActivity());
                Toast.makeText(getActivity(), "insert row id" + id, Toast.LENGTH_SHORT).show();
                mainViewModel.dbSellectAll(requireActivity());
            }

            }
        });


        setValues(mainViewModel.user);

        btBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();

            }
        });
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);
        itemDelete = menu.findItem(R.id.action_delete);
        itemDelete.setVisible(false);
        itemEdit = menu.findItem(R.id.action_edit);
        itemEdit.setVisible(false);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    //open dialog of delete
    public void openDeleteDialog(){
        AlertDialog.Builder alretDialog = new AlertDialog.Builder(requireActivity());
        alretDialog.setTitle("Delete");
        alretDialog.setMessage("Are you want delete?");
        alretDialog.setCancelable(true);
        alretDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() { //what hapend if choose yes
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mainViewModel.dbDelete(requireActivity());
                dialogInterface.dismiss();
            }
        });
        alretDialog.setNegativeButton("No", new DialogInterface.OnClickListener() { //what hapend if choose no
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            }
        });
        alretDialog.show();
    }



    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id){
            case R.id.action_delete:
                openDeleteDialog();  //open dialog and delete
                return true;

            case R.id.action_edit:
                mainViewModel.currentUser.setName(etFragmentUserName.getText()+"");
                mainViewModel.dbupdate(requireActivity());
                return true;
        }

        return false;
    }
    public void setValues(User user){
        tvRating.setText("Rate:" + user.getRate()+"");
        etFragmentUserName.setText(user.getName());
        tvScore.setText("score:" + user.getScore());

    }

}
