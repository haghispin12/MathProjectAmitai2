package com.example.mathprojectamitai2;

public class game {
    private String Uid1;
    private String Uid2;
    private int status;

    public game(String uid1, String uid2, int status) {
        Uid1 = uid1;
        this.status = 0;
    }

    public String getUid1() {
        return Uid1;
    }

    public void setUid1(String uid1) {
        Uid1 = uid1;
    }

    public String getUid2() {
        return Uid2;
    }

    public void setUid2(String uid2) {
        Uid2 = uid2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
