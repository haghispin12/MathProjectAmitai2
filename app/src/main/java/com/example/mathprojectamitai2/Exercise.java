package com.example.mathprojectamitai2;

import java.util.Random;

public class Exercise {

    private int num1;
    private int num2;

    public void setBtChallenge(){
        Random r = new Random();
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(89) + 10;
    }

    public void setBtUntiltwenty(){
        Random r = new Random();
        num1 = r.nextInt(8) + 1;
        num2 = r.nextInt(10) + 10;
    }

    public void setBtMultiplicationTable(){
        Random r = new Random();
        num1 = r.nextInt(9) + 1;
        num2 = r.nextInt(9) + 1;
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public boolean check(String answer){
        int result = num1*num2;
        String res = result + "";
        if(answer.equals(res)) {
            return true;
        }else
            return false;
    }




}
