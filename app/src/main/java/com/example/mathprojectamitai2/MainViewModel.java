package com.example.mathprojectamitai2;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise exercise;
    User user;
    int Type;


    //constructor
    public MainViewModel(){
        vNum1=new MutableLiveData<>();
        vNum2=new MutableLiveData<>();
        exercise = new Exercise();
        user = new User();
    }

    public void vChallenge(){
    exercise.setBtChallenge();
     vNum1.setValue(exercise.getNum1());
     vNum2.setValue(exercise.getNum2());
     Type = 20;
    }

    public void vMultiplicationTable(){
    exercise.setBtMultiplicationTable();
    vNum1.setValue(exercise.getNum1());
    vNum2.setValue(exercise.getNum2());
    Type = 10;
    }

    public void vUntiltwenty(){
    exercise.setBtUntiltwenty();
    vNum1.setValue(exercise.getNum1());
    vNum2.setValue(exercise.getNum2());
    Type = 15;
    }

    public boolean vCheck(String answer) {
        boolean b1 = exercise.check(answer);
     return b1;
    }
    public void updateRate(int rate){
        user.setRate(rate);
    }

    public void updateName(String name){
        user.setName(name);

    }

    public void setScore(int score){
        int sc = user.getScore() + score;
        user.setScore(sc);
    }

    public long dbAddUser(Context context){
        DBHelper dbHelper = new DBHelper(context);
        long id = dbHelper.insert(user, context);
        Log.d("responseId", id+"");

        return id;
}

    public int getScore(){
        return user.getScore();
    }



}
