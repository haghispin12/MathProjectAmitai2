package com.example.mathprojectamitai2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise exercise;

    //constructor
    public MainViewModel(){
        vNum1=new MutableLiveData<>();
        vNum2=new MutableLiveData<>();
        exercise = new Exercise();
    }

    public void vChallenge(){
    exercise.setBtChallenge();
     vNum1.setValue(exercise.getNum1());
     vNum2.setValue(exercise.getNum2());
    }



}
