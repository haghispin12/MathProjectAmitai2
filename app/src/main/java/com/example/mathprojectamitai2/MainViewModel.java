package com.example.mathprojectamitai2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise exercise;
    User user;


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
    }

    public void vMultiplicationTable(){
    exercise.setBtMultiplicationTable();
    vNum1.setValue(exercise.getNum1());
    vNum2.setValue(exercise.getNum2());
    }

    public void vUntiltwenty(){
    exercise.setBtUntiltwenty();
    vNum1.setValue(exercise.getNum1());
    vNum2.setValue(exercise.getNum2());
    }

    public boolean vCheck(String answer) {
        boolean b1 = exercise.check(answer);
     return b1;
    }

    public void updateName(String name){
        user.setName(name);
    }

}
